package com.example.stocker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stocker.domain.Order;
import com.example.stocker.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    public void insertOrder (Order order) {
        orderRepository.insertOrder(order);
    }
}
