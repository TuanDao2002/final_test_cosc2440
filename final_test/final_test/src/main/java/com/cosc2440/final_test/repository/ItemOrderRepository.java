package com.cosc2440.final_test.repository;

import com.cosc2440.final_test.model.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer> {
    List<ItemOrder> findAllByOrderByIdAsc();
    List<ItemOrder> findAllByOrderByIdDesc();
}
