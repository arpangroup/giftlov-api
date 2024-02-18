package com.arpan.giftlovapi.service.impl;

import com.arpan.giftlovapi.model.Catalog;
import com.arpan.giftlovapi.model.Item;
import com.arpan.giftlovapi.model.ItemAvailability;
import com.arpan.giftlovapi.repository.CatalogDB;
import com.arpan.giftlovapi.service.CatalogService;
import com.arpan.giftlovapi.util.JsonReaderUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogServiceImpl implements CatalogService {
    private final CatalogDB catalogRepository;
    private final RestTemplate restTemplate;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Value("${catalog.disable-giftlov-api:true}")
    private boolean isGiftlovCatalogApiDisabled;

    @Value("${endpoint.catalogUri:/items}")
    private String catalogUri;

    @Override
    public List<Item> getAllItems() {
        List<Item> items = catalogRepository.getItems();
        syncCatalogItems();
        return items;
    }


    private Future<List<Item>> syncCatalogItems () {
        if (isGiftlovCatalogApiDisabled) {
            return executor.submit(() -> {
                List<Item> jsonItems = getCatalogItemsFromJson();
                catalogRepository.putItems(jsonItems);
                List<Item> items = catalogRepository.getItems();
                return jsonItems;
            });
        } else {
            return executor.submit(() -> {
                List<Item> remoteItems = getCatalogItemsFromApi();
                catalogRepository.putItems(remoteItems);
                return remoteItems;
            });
        }
    }

    private List<Item> getCatalogItemsFromApi() {
        List<Item> items = new ArrayList<>();
        try {
            ResponseEntity<Catalog> response = restTemplate.getForEntity(catalogUri, Catalog.class);
            if(response.getBody() != null) {
                items = response.getBody().getItems();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    private List<Item> getCatalogItemsFromJson() {
        return JsonReaderUtil.loadCatalog();
    }

    @Override
    public ItemAvailability checkItemAvailability(@NonNull String cardIdentifier, @NonNull String value) {
        return null;
    }
}
