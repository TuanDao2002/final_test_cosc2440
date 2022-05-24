package com.cosc2440.final_test.service;

import com.cosc2440.final_test.model.ItemOrder;
import com.cosc2440.final_test.repository.ItemOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemOrderService {
    @Autowired
    private ItemOrderRepository itemOrderRepository;

    public ItemOrderService(){}

    public void setItemOrderRepository(ItemOrderRepository itemOrderRepository) {
        this.itemOrderRepository = itemOrderRepository;
    }

    public ItemOrder add(ItemOrder itemOrder) {
        return itemOrderRepository.save(itemOrder);
    }


}
