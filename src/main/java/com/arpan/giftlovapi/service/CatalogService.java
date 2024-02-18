package com.arpan.giftlovapi.service;

import com.arpan.giftlovapi.model.Item;
import com.arpan.giftlovapi.model.ItemAvailability;
import lombok.NonNull;

import java.util.List;

public interface CatalogService {
    List<Item> getAllItems();
    List<Item> getAllItems(int current, int rowCount, boolean includePricingDetails, String searchPhrase);
    ItemAvailability checkItemAvailability(@NonNull String cardIdentifier, @NonNull String value);

}
