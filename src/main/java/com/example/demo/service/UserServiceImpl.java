package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;

    @Override
    public User create(User user) {
        User savedUser = userRepository.save(user);
        UserRole userRole = new UserRole("ROLE_USER", user);
        emailService.sendSimpleMessage(user.getEmail(), "Activate your account!", "Your activation code is " + user.getSecurityCode());
        return savedUser;
    }

    @Override
    public User emailActivation(User user, Integer activationCode) {
        if(activationCode != null && user != null){
            if(user.getSecurityCode().equals(activationCode)){
                user.setIsActivated(1);
                return userRepository.save(user);
            }
        }
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
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public User getById(Long id){
        return userRepository.findById(id).get();
    }
}
