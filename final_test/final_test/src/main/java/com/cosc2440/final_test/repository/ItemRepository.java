package com.cosc2440.final_test.repository;

import com.cosc2440.final_test.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findAllByOrderByIdAsc();
    List<Item> findAllByOrderByIdDesc();
}
