package com.example.demo.bootstrap;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MainBootstrap implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    EmailService emailService;
    @Autowired


    @Override
    public void run(ApplicationArguments args) throws Exception {
    try {


    }catch (Exception ex){
        System.out.println(ex.getCause()+ex.getMessage());
    }finally {
        System.out.println("Your app is working!");
    }
    }
}
