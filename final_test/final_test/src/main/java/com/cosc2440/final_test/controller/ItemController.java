package com.cosc2440.final_test.controller;

import com.cosc2440.final_test.model.Item;
import com.cosc2440.final_test.model.ItemOrder;
import com.cosc2440.final_test.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(path = "/item", method = RequestMethod.GET)
    public List<Item> get(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "price", required = false) Double price,
                               @RequestParam(value = "order", required = false) String order) {
        return itemService.get(name, price, order);
    }

    @RequestMapping(path = "/item", method = RequestMethod.POST)
    public Item add(@RequestBody Item item) throws Exception {
        return itemService.add(item);
    }

    @RequestMapping(path = "/item", method = RequestMethod.PUT)
    public Item update(@RequestBody Item item) throws Exception {
        return itemService.update(item);
    }

    @RequestMapping(path = "/item/{id}", method = RequestMethod.DELETE)
    public HttpStatus deleteById(@PathVariable Integer id) {
        return itemService.deleteById(id);
    }
}
