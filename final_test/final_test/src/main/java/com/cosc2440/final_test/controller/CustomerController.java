package com.cosc2440.final_test.controller;

import com.cosc2440.final_test.model.Customer;
import com.cosc2440.final_test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(path = "/customer", method = RequestMethod.GET)
    public List<Customer> get(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                              @RequestParam(value = "order", required = false) String order) {
        return customerService.get(name, phoneNumber, order);
    }

    @RequestMapping(path = "/customer", method = RequestMethod.POST)
    public Customer add(@RequestBody Customer customer) {
        return customerService.add(customer);
    }

    @RequestMapping(path = "/customer", method = RequestMethod.PUT)
    public Customer update(@RequestBody Customer customer) throws Exception {
        return customerService.update(customer);
    }

}
