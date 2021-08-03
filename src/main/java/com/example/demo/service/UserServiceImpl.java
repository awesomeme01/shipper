package com.example.demo.service;

import com.example.demo.helper.PasswordChangeWrapper;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class UserServiceImpl implements UserService{
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    EmailService emailService;

    @Override
    public User create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        UserRole userRole = new UserRole("ROLE_USERNOTACTIVATED", user);
        userRoleRepository.save(userRole);
        Random rand = new Random();
        Integer securityCode = rand.nextInt(999999 - 100000) + 100000;
        emailService.sendSimpleMessage(user.getEmail(), "Activate your account!", "Your activation code is " + securityCode);
        user.setSecurityCode(encoder.encode(Integer.toString(securityCode)));
        return savedUser;
    }

    @Override
    public User emailActivation(User user, String activationCode) {
        if(activationCode != null && user != null){
            if(user.getSecurityCode().equals(encoder.encode(activationCode))){
                user.setIsActivated(1);
                UserRole userRole = new UserRole("ROLE_USER", user);
                userRoleRepository.save(userRole);
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

    @Override
    public User preChangePassword(User user) {
        Random rand = new Random();
        Integer securityCode = rand.nextInt(999999 - 100000) + 100000;
        emailService.sendSimpleMessage(user.getEmail(), "Password change attempt", "Here is the security code to change your password! SecurityCode: " + securityCode);
        user.setSecurityCode(encoder.encode(Integer.toString(securityCode)));
        return user;
    }

    @Override
    public User changePassword(PasswordChangeWrapper p, User user) {
        if(user.getSecurityCode()!=null && p.getSecurityCode()!=null&&p.getOldPassword()!=null&&p.getNewPassword()!=null){
            if(user.getSecurityCode().equals(p.getSecurityCode()) && user.getPassword().equals(encoder.encode(p.getOldPassword()))){
                if(!p.getNewPassword().equals(p.getOldPassword())){
                    user.setPassword(encoder.encode(p.getNewPassword()));
                    return userRepository.save(user);
                }
            }
        }
        emailService.sendSimpleMessage(user.getEmail(), "WARNING! There was an attempt to change your password!", "There was an attempt to change your password! Please contact us if it wasn't you!");
        return null;
    }
}

