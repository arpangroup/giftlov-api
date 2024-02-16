package com.arpan.giftlovapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogs")
@Slf4j
@RequiredArgsConstructor
public class CatalogueController {

    @GetMapping
    public String getAllCatalogs() {
        return "All Catalogs....";
    }


}
