package com.cosc2440.final_test.controller;

import com.cosc2440.final_test.model.Deliveryman;
import com.cosc2440.final_test.service.DeliverymanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliverymanController {
    @Autowired
    private DeliverymanService deliverymanService;

    public void setDeliverymanService(DeliverymanService deliverymanService) {
        this.deliverymanService = deliverymanService;
    }

    @RequestMapping(path = "/deliveryman", method = RequestMethod.GET)
    public List<Deliveryman> get(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                 @RequestParam(value = "order", required = false) String order) {
        return deliverymanService.get(name, phoneNumber, order);
    }

    @RequestMapping(path = "/deliveryman", method = RequestMethod.POST)
    public Deliveryman add(@RequestBody Deliveryman deliveryman) {
        return deliverymanService.add(deliveryman);
    }

    @RequestMapping(path = "/deliveryman", method = RequestMethod.PUT)
    public Deliveryman update(@RequestBody Deliveryman deliveryman) throws Exception {
        return deliverymanService.update(deliveryman);
    }

    @RequestMapping(path = "/deliveryman/{id}", method = RequestMethod.DELETE)
    public HttpStatus deleteById(@PathVariable Integer id) {
        return deliverymanService.deleteById(id);
    }
}
