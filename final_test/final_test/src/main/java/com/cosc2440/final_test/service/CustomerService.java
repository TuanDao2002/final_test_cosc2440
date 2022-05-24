package com.cosc2440.final_test.service;

import com.cosc2440.final_test.model.Customer;
import com.cosc2440.final_test.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(){}

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> search(String name, String phoneNumber, String order) {
        List<Customer> customers;
        if (order != null && order.equalsIgnoreCase("desc")) {
            customers = customerRepository.findAllByOrderByIdDesc();
        } else {
            customers = customerRepository.findAllByOrderByIdAsc();
        }

        if (name != null) {
            customers.removeIf(customer -> !customer.getName().equalsIgnoreCase(name));
        }

        if (phoneNumber != null) {
            customers.removeIf(customer -> !customer.getPhoneNumber().equalsIgnoreCase(phoneNumber));
        }

        return customers;
    }

    public List<Customer> get(String name, String phoneNumber, String order) {
        return search(name, phoneNumber, order);
    }

    public Customer update(Customer customer) throws Exception {
        Optional<Customer> findCustomer = customerRepository.findById(customer.getId());
        if (findCustomer.isEmpty()) throw new Exception("Customer not found!!!");
        Customer updateCustomer = findCustomer.get();
        if (customer.getName() != null) updateCustomer.setName(customer.getName());
        if (customer.getAddress() != null) updateCustomer.setAddress(customer.getAddress());
        if (customer.getPhoneNumber() != null) updateCustomer.setPhoneNumber(customer.getPhoneNumber());
        return updateCustomer;
    }
}
