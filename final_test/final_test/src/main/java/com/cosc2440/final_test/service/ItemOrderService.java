package com.cosc2440.final_test.service;

import com.cosc2440.final_test.model.Customer;
import com.cosc2440.final_test.model.Deliveryman;
import com.cosc2440.final_test.model.ItemOrder;
import com.cosc2440.final_test.model.ItemOrder;
import com.cosc2440.final_test.repository.CustomerRepository;
import com.cosc2440.final_test.repository.DeliverymanRepository;
import com.cosc2440.final_test.repository.ItemOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemOrderService {
    @Autowired
    private ItemOrderRepository itemOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeliverymanRepository deliverymanRepository;

    public ItemOrderService(){}

    public void setItemOrderRepository(ItemOrderRepository itemOrderRepository) {
        this.itemOrderRepository = itemOrderRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setDeliverymanRepository(DeliverymanRepository deliverymanRepository) {
        this.deliverymanRepository = deliverymanRepository;
    }

    public ItemOrder add(ItemOrder itemOrder) throws Exception {
        Optional<Customer> findCustomer = customerRepository.findById(itemOrder.getCustomer().getId());
        if (findCustomer.isEmpty()) throw new Exception("Customer not found!!!");
        Optional<Deliveryman> findDeliveryman = deliverymanRepository.findById(itemOrder.getDeliveryman().getId());
        if (findDeliveryman.isEmpty()) throw new Exception("Deliveryman not found!!!");

        ItemOrder newItemOrder = itemOrderRepository.save(itemOrder);
        findCustomer.get().getOrders().add(newItemOrder);
        findDeliveryman.get().getOrders().add(newItemOrder);
        return newItemOrder;
    }

    public List<ItemOrder> search(Integer id, Date createdDate, String order) {
        List<ItemOrder> itemOrders;
        if (order != null && order.equalsIgnoreCase("desc")) {
            itemOrders = itemOrderRepository.findAllByOrderByIdDesc();
        } else {
            itemOrders = itemOrderRepository.findAllByOrderByIdAsc();
        }

        if (id != null) {
            itemOrders.removeIf(itemOrder -> !itemOrder.getId().equals(id));
        }

        if (createdDate != null) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            itemOrders.removeIf(itemOrder -> !fmt.format(itemOrder.getCreatedDate()).equals(fmt.format(createdDate)));
        }

        return itemOrders;
    }

    public List<ItemOrder> get(Integer id, Date createdDate, String order) {
        return search(id, createdDate, order);
    }

    public ItemOrder update(ItemOrder itemOrder) throws Exception {
        Optional<ItemOrder> findItemOrder = itemOrderRepository.findById(itemOrder.getId());
        if (findItemOrder.isEmpty()) throw new Exception("Order not found!!!");
        ItemOrder updateItemOrder = findItemOrder.get();
        if (itemOrder.getTotalPrice() != 0) updateItemOrder.setTotalPrice(itemOrder.getTotalPrice());
        if (itemOrder.getCreatedDate() != null) updateItemOrder.setCreatedDate(itemOrder.getCreatedDate());
        return updateItemOrder;
    }

    public HttpStatus deleteById(Integer id) {
        Optional<ItemOrder> findItemOrder = itemOrderRepository.findById(id);
        if (findItemOrder.isEmpty()) return HttpStatus.NOT_FOUND;
        itemOrderRepository.deleteById(id);
        return HttpStatus.ACCEPTED;
    }
}
