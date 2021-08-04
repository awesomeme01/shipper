package com.example.demo.service;

import com.example.demo.helper.Status;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    @Override
    public Order create(Order order) {
     return   orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
       return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }


    @Override
    public List<Order> getMyOrders(User user) {
        return  orderRepository.findAll().stream().filter(x->x.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public Order updateStatus(Long orderId, Status status) {
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public Order updateTotal(Long orderId, Double total) {
        Order order = orderRepository.findById(orderId).get();
        order.setTotal(total);
        return orderRepository.save(order);
    }
}
