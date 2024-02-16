package com.arpan.giftlovapi.service;

import com.arpan.giftlovapi.model.Item;
import com.arpan.giftlovapi.model.ItemAvailability;
import lombok.NonNull;

import java.util.List;

public interface CatalogService {
    List<Item> getAllItems();
    ItemAvailability checkItemAvailability(@NonNull String cardIdentifier, @NonNull String value);
}
