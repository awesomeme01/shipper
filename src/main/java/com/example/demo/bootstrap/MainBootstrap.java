package com.example.demo.bootstrap;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setName("Shabdan");
        user.setSurName("Abzhaparov");
        user.setEmail("shabdan.abjaparov.official@gmail.com");
        user.setIsActivated(1);
        user.setPassword(encoder.encode("1267476Sha"));
        user.setPhoneNumber("996550523209");
        userRepository.save(user);
        UserRole userRole = new UserRole("ROLE_ADMIN", user);
        UserRole userRole2 = new UserRole("ROLE_USER", user);
        userRoleRepository.save(userRole);
        userRoleRepository.save(userRole2);
//        User user = userRepository.findByUsername("sh.abjaparov@gmail.com");
//        String newPassword = encoder.encode(user.getPassword());
//        user.setPassword(newPassword);
//        userRepository.save(user);




    }catch (Exception ex){
        System.out.println(ex.getCause()+ex.getMessage());
        emailService.sendSimpleMessage("shabdan.abjaparov.official@gmail.com", "App crashed!", ex.getMessage());

    }finally {
        System.out.println("Your app is working!");
        emailService.sendSimpleMessage("shabdan.abjaparov.official@gmail.com", "App started!", "Your app is working fine!");
        String message = "";
        for(User u : userRepository.findAll()){
            System.out.println(u);
            message += u;
        }
        emailService.sendSimpleMessage("shabdan.abjaparov.official@gmail.com", "App started!", message);

    }
    }
}
