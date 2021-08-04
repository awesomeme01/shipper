package com.example.demo.service;

import com.example.demo.helper.OrderTotalUpdateWrapper;
import com.example.demo.helper.Status;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
         return orderRepository.save(order);
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
    public Boolean exists(Long id) {
        return orderRepository.existsById(id);
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
    public Order updateTotal(Long orderId, OrderTotalUpdateWrapper wrapper) {
        Order order = orderRepository.findById(orderId).get();
        order.setTotal(wrapper.getTotal());
        order.setVolume(wrapper.getVolume());
        order.setVolumeUnit(wrapper.getVolumeUnit());
        return orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).get();
    }
}
