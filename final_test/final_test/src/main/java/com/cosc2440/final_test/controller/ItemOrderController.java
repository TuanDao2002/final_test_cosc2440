package com.cosc2440.final_test.controller;
import com.cosc2440.final_test.model.ItemOrder;
import com.cosc2440.final_test.service.ItemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ItemOrderController {
    @Autowired
    private ItemOrderService itemOrderService;

    public void setItemOrderService(ItemOrderService itemOrderService) {
        this.itemOrderService = itemOrderService;
    }

    @RequestMapping(path = "/order", method = RequestMethod.GET)
    public List<ItemOrder> get(@RequestParam(value = "id", required = false) Integer id,
                               @RequestParam(value = "createdDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdDate,
                               @RequestParam(value = "order", required = false) String order) {
        return itemOrderService.get(id, createdDate, order);
    }

    @RequestMapping(path = "/order", method = RequestMethod.POST)
    public ItemOrder add(@RequestBody ItemOrder itemOrder) throws Exception {
        return itemOrderService.add(itemOrder);
    }

    @RequestMapping(path = "/order", method = RequestMethod.PUT)
    public ItemOrder update(@RequestBody ItemOrder itemOrder) throws Exception {
        return itemOrderService.update(itemOrder);
    }

    @RequestMapping(path = "/order/{id}", method = RequestMethod.DELETE)
    public HttpStatus deleteById(@PathVariable Integer id) {
        return itemOrderService.deleteById(id);
    }
}
