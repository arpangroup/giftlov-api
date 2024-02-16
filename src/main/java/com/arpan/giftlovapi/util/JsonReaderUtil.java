package com.arpan.giftlovapi.util;

import com.arpan.giftlovapi.model.Catalog;
import com.arpan.giftlovapi.model.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonReaderUtil {
    private static final Type CATALOG_ITEM_TYPE = new TypeToken<List<Item>>() {}.getType();

    public static List<Item> loadCatalog(){
        String fileName = "src/main/resources/catalog.json";
        Gson gson = new Gson();
        List<Item> items = new ArrayList<>();

        try{

            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Catalog catalog = gson.fromJson(reader, Catalog.class);
            items = catalog.getItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}
