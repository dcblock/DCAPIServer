package com.digicap.dcblock.caffeapiserver.controller;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicap.dcblock.caffeapiserver.dto.MenuDto;
import com.digicap.dcblock.caffeapiserver.service.MenuService;

/**
 * 카페에서 사용하는 메뉴 Controller Class.
 *
 * @author DigiCAP
 */
@RestController
public class MenuController {

    private MenuService service;

    // -------------------------------------------------------------------------
    // Constructor

    @Autowired
    public MenuController(MenuService service) {
        this.service = service;
    }

    // -------------------------------------------------------------------------
    // Public Methods

    @GetMapping("/api/caffe/menus")
    LinkedHashMap<String, LinkedList<MenuDto>> getAllMenus() {
        LinkedHashMap<String, LinkedList<MenuDto>> menus = service.getAllMenus();
        return menus;
    }

    @PostMapping(value = "/api/caffe/menus", consumes = "application/json; charset=utf-8")
    MenuDto insertMenu(@RequestBody MenuDto body) {
        MenuDto result = service.setMenu(body);
        return result;
    }

    @PatchMapping(value = "/api/caffe/menus/{category}", consumes = "application/json; charset=utf-8")
    LinkedList<MenuDto> updateMenuInCategory(@PathVariable("category") int category,
                                             @RequestBody LinkedList<MenuDto> body) {
        // Check Argument.
        Preconditions.checkArgument(category > 0, "invalid category(%d)", category);

        return service.updateAllMenusInCategory(category, body);
    }

    @DeleteMapping("/api/caffe/menus/{category}/{code}")
    HashMap<String, String> deleteMenu(@PathVariable("category") int category,
                                       @PathVariable("code") int code) {
        // Check Argument.
        Preconditions.checkArgument(category >= 0, "invalid category(%d)", category);
        Preconditions.checkArgument(code >= 0, "invalid code(%d)", code);

        service.deleteMenu(category, code);
        return new HashMap<>();
    }

    // -------------------------------------------------------------------------
    // Version 2

    @GetMapping(value = "/api/caffe/menus", produces = "application/vnd.digicap.v2+json")
    LinkedHashMap<String, LinkedList<MenuDto>> getAllMenusV2() {
        LinkedHashMap<String, LinkedList<MenuDto>> menus = service.getAllMenus();
        return menus;
    }
}
