package com.arpan.giftlovapi.service.impl;

import com.arpan.giftlovapi.model.Item;
import com.arpan.giftlovapi.model.ItemAvailability;
import com.arpan.giftlovapi.repository.CatalogDB;
import com.arpan.giftlovapi.service.CatalogService;
import com.arpan.giftlovapi.util.JsonReaderUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CatalogDB catalogDB;

    @Override
    public List<Item> getAllItems() {
        return JsonReaderUtil.loadCatalog();
    }

    @Override
    public ItemAvailability checkItemAvailability(@NonNull String cardIdentifier, @NonNull String value) {
        return null;
    }
}
