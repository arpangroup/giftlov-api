package com.arpan.giftlovapi.repository;

import com.arpan.giftlovapi.model.Item;
import com.arpan.giftlovapi.util.JsonReaderUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CatalogDB {
    private final List<Item> catalogDB;

    List<Item> getAllCatalogItems() {
        return this.catalogDB;
    }

    public void putItem(Item item) {
        if (getItem(item.getId()) != null){
            catalogDB.add(item);
        }
    }
    public Item getItem(String itemId) {
        return catalogDB.stream().filter(it -> it.getId().equals(itemId)).findFirst().orElse(null);
    }

    public void putItems(List<Item> items) {
        items.forEach(this::putItem);
    }
}
