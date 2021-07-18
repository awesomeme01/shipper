package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User emailActivation(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleted(Long id) {
    userRepository.deleteById(id);
    }

    @Override
    public List<User> getMyUsers() {
        return userRepository.findAll();
    }
    @Override
    public User getById(Long id){
        return userRepository.findById(id).get();
    }
}
