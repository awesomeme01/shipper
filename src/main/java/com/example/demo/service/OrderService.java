package com.example.demo.service;

import com.example.demo.helper.OrderTotalUpdateWrapper;
import com.example.demo.helper.Status;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;

import java.util.List;

public interface OrderService{
    Order create (Order order);
    Order update (Order order);
    void delete (Long id);
    Boolean exists(Long id);
    List<Order> getMyOrders (User user);
    Order updateStatus(Long orderId,Status status);
    Order updateTotal(Long orderId, OrderTotalUpdateWrapper wrapper);
}
