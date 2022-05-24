package com.cosc2440.final_test.service;

import com.cosc2440.final_test.model.Deliveryman;
import com.cosc2440.final_test.repository.DeliverymanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeliverymanService {
    @Autowired
    private DeliverymanRepository deliverymanRepository;

    public void setDeliverymanRepository(DeliverymanRepository deliverymanRepository) {
        this.deliverymanRepository = deliverymanRepository;
    }

    public Deliveryman add(Deliveryman deliveryman) {
        return deliverymanRepository.save(deliveryman);
    }

    public List<Deliveryman> search(String name, String phoneNumber, String order) {
        List<Deliveryman> deliverymans;
        if (order != null && order.equalsIgnoreCase("desc")) {
            deliverymans = deliverymanRepository.findAllByOrderByIdDesc();
        } else {
            deliverymans = deliverymanRepository.findAllByOrderByIdAsc();
        }

        if (name != null) {
            deliverymans.removeIf(deliveryman -> !deliveryman.getName().equalsIgnoreCase(name));
        }

        if (phoneNumber != null) {
            deliverymans.removeIf(deliveryman -> !deliveryman.getPhoneNumber().equalsIgnoreCase(phoneNumber));
        }

        return deliverymans;
    }

    public List<Deliveryman> get(String name, String phoneNumber, String order) {
        return search(name, phoneNumber, order);
    }

    public Deliveryman update(Deliveryman deliveryman) throws Exception {
        Optional<Deliveryman> findDeliveryman = deliverymanRepository.findById(deliveryman.getId());
        if (findDeliveryman.isEmpty()) throw new Exception("Deliveryman not found!!!");
        Deliveryman updateDeliveryman = findDeliveryman.get();
        if (deliveryman.getName() != null) updateDeliveryman.setName(deliveryman.getName());
        if (deliveryman.getPhoneNumber() != null) updateDeliveryman.setPhoneNumber(deliveryman.getPhoneNumber());
        return updateDeliveryman;
    }

    public HttpStatus deleteById(Integer id) {
        Optional<Deliveryman> findDeliveryman = deliverymanRepository.findById(id);
        if (findDeliveryman.isEmpty()) return HttpStatus.NOT_FOUND;
        deliverymanRepository.deleteById(id);
        return HttpStatus.ACCEPTED;
    }
}
