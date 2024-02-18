package com.arpan.giftlovapi.repository;

import com.arpan.giftlovapi.model.Item;
import com.arpan.giftlovapi.util.JsonReaderUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class CatalogDB {
    private List<Item> catalogDB;

    public CatalogDB() {
        this.catalogDB = new ArrayList<>();
    }

    List<Item> getAllCatalogItems() {
        return this.catalogDB;
    }

    public List<Item> getItems() {
        return this.catalogDB != null ? this.catalogDB : new ArrayList<>();
    }

    public void putItem(Item item) {
        boolean isItemExist = getItem(item.getId()) != null;
        if (!isItemExist){
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
