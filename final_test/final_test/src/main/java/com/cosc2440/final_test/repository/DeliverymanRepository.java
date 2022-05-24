package com.cosc2440.final_test.repository;

import com.cosc2440.final_test.model.Deliveryman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliverymanRepository extends JpaRepository<Deliveryman, Integer> {
    List<Deliveryman> findAllByOrderByIdAsc();
    List<Deliveryman> findAllByOrderByIdDesc();
}
