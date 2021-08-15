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
import java.util.List;


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
        Random rand = new Random();
        Integer securityCode = rand.nextInt(999999 - 100000) + 100000;
        emailService.sendSimpleMessage(user.getEmail(), "Activate your account!", "Your activation code is " + securityCode);
        user.setSecurityCode(encoder.encode(securityCode+""));
        User savedUser = userRepository.save(user);
        UserRole userRole = new UserRole("ROLE_USERNOTACTIVATED", user);
        userRoleRepository.save(userRole);
        return savedUser;
    }
    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public User emailActivation(User user, String activationCode) {
        if(activationCode != null && user != null && user.getSecurityCode()!=null){
            if(encoder.matches(activationCode, user.getSecurityCode())){
                user.setIsActivated(1);
                UserRole userRole = new UserRole("ROLE_USER", user);
                userRoleRepository.save(userRole);
                return userRepository.save(user);
            }
        }
        return null;
    }

    @Override
    public void sendSecurityCode(User user) {
        if(user.getIsActivated() == 1){
            emailService.sendSimpleMessage(user.getEmail(), "Your account is already activated!", "Having other problems with your account? Contact us!");

        }else{
            emailService.sendSimpleMessage(user.getEmail(), "Activate your account!", "You " + user.getSecurityCode());
        }
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
                    emailService.sendSimpleMessage(user.getEmail(), "WARNING! Your password has been changed!", "The password for your account has been changed! Please contact us if it wasn't you!");
                    return userRepository.save(user);
                }
            }
        }
        emailService.sendSimpleMessage(user.getEmail(), "WARNING! There was an attempt to change your password!", "There was an attempt to change your password! Please contact us if it wasn't you!");
        return null;
    }
}

