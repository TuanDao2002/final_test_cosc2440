package com.cosc2440.final_test.repository;

import com.cosc2440.final_test.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByOrderByIdAsc();
    List<Customer> findAllByOrderByIdDesc();
}
