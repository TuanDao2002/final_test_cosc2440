package com.cosc2440.final_test.service;

import com.cosc2440.final_test.model.Item;
import com.cosc2440.final_test.model.ItemOrder;
import com.cosc2440.final_test.repository.ItemOrderRepository;
import com.cosc2440.final_test.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemOrderRepository itemOrderRepository;

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void setItemOrderRepository(ItemOrderRepository itemOrderRepository) {
        this.itemOrderRepository = itemOrderRepository;
    }

    public Item add(Item item) throws Exception {
        Optional<ItemOrder> findItemOrder = itemOrderRepository.findById(item.getOrderID());
        if (findItemOrder.isEmpty()) throw new Exception("Order not found!!!");

        Item newItem = itemRepository.save(item);
        findItemOrder.get().setTotalPrice(findItemOrder.get().getTotalPrice() + newItem.getPrice());
        findItemOrder.get().getItems().add(newItem);

        return newItem;
    }

    public List<Item> search(String name, Double price, String order) {
        List<Item> items;
        if (order != null && order.equalsIgnoreCase("desc")) {
            items = itemRepository.findAllByOrderByIdDesc();
        } else {
            items = itemRepository.findAllByOrderByIdAsc();
        }

        if (name != null) {
            items.removeIf(item -> !(item.getName().equals(name)));
        }

        if (price != null) {
            items.removeIf(item -> !(item.getPrice() == price));
        }

        return items;
    }

    public List<Item> get(String name, Double price, String order) {
        return search(name, price, order);
    }

    public Item update(Item item) throws Exception {
        Optional<Item> findItem = itemRepository.findById(item.getId());
        if (findItem.isEmpty()) throw new Exception("Item not found!!!");
        Item updateItem = findItem.get();
        if (item.getName() != null) updateItem.setName(item.getName());
        if (item.getPrice() != 0) updateItem.setPrice(item.getPrice());
        return updateItem;
    }

    public HttpStatus deleteById(Integer id) {
        Optional<Item> findItem = itemRepository.findById(id);
        if (findItem.isEmpty()) return HttpStatus.NOT_FOUND;
        itemRepository.deleteById(id);
        return HttpStatus.ACCEPTED;
    }
}
