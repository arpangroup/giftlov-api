package com.arpan.giftlovapi.controller;

import com.arpan.giftlovapi.model.AuthTokenHolder;
import com.arpan.giftlovapi.model.Item;
import com.arpan.giftlovapi.service.CatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
@Slf4j
@RequiredArgsConstructor
public class CatalogueController {
    private final CatalogService catalogService;

    @GetMapping
    public List<Item> getAllCatalogs(
            @RequestParam(value = "current", defaultValue = "1") int current,
            @RequestParam(value = "rowCount", defaultValue = "10") int rowCount,
            @RequestParam(value = "includePricingDetails", defaultValue = "false") boolean includePricingDetails,
            @RequestParam(value = "searchPhrase", defaultValue = "") String searchPhrase
    ) {
        return catalogService.getAllItems(current, rowCount, includePricingDetails, searchPhrase);
    }
}
