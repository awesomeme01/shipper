package com.example.demo.controller;

import com.example.demo.helper.*;
import com.example.demo.model.User;

import com.example.demo.model.UserRole;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    EmailService emailService;
    @PostMapping("/register")
    public Response create (@RequestBody User user){
        try{
            return new Response(true, "New User created!", userService.create(user));
        }catch(Exception ex){
            return new Response(false, "Unexpected error", new ExceptionWrapper(ex));
        }
    }
    @Secured("ROLE_USERNOTACTIVATED")
    @PostMapping("/activateUser")
    public Response activateUser(Principal principal, @RequestBody ActivationCodeWrapper awrapper){
        try{
            User user = userService.emailActivation(userService.getByUsername(principal.getName()), awrapper.getActivationCode());
            if(user!= null){
                return new Response(true, "User Successfully Activated!", user);
            }else{
                return new Response(false, "Activation code didn't match", null);
            }
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", new ExceptionWrapper(ex));
        }

    }

    @Secured("ROLE_USER")
    @PostMapping("/saveDocumentInfo")
    public Response saveDocumentInfo(Principal principal, @RequestBody DocumentInfoWrapper wrapper){
        try{
            User user = userService.getByUsername(principal.getName());
            user.setDocumentNumber(wrapper.getDocumentNumber());
            user.setDocumentType(wrapper.getDocumentType());
            user.setAddress(wrapper.getAddress());
            user.setCountry(wrapper.getCountry());
            return new Response(true, "User's document information was successfully saved!", userService.update(user));
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", new ExceptionWrapper(ex));
        }
    }

    @Secured("ROLE_USERNOTACTIVATED")
    @GetMapping("/getSecurityCode")
    public Response getSecurityCode(Principal principal){
        try{
            userService.sendSecurityCode(userService.getByUsername(principal.getName()));
            return new Response(true, "Security code sent to your email", null);
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", new ExceptionWrapper(ex));
        }
    }


    @Secured("ROLE_USER")
    @PostMapping("/update")
    public Response update(Principal p, @RequestBody User user){
        try{
            User user2 = userService.getByUsername(p.getName());
            if(user.getId().equals(user2.getId())){
                return new Response(true, "Updated existing user", userService.update(user),
                        userRoleService.getByUser(user).stream().map(UserRole::getRole).collect(Collectors.toList()));
            }
            else{
                return new Response(false, "Current users cannot access user with id = " + user.getId(), null);
            }
        }catch (Exception ex){
            return new Response(true, "Unexpected error", new ExceptionWrapper(ex));
        }
    }

    @GetMapping("/getMyUser")
    public Response getMyUser(Principal principal){
        try{
            return new Response(true, "Current user information", userService.getByUsername(principal.getName()));
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", new ExceptionWrapper(ex));
        }
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/getById/{id}")
    public Response getById(@PathVariable Long id){
        try{
            return new Response(true, "User with id = " + id, userService.getById(id));
        }catch(Exception ex){
            return new Response(false, "Unexpected error!", new ExceptionWrapper(ex));
        }
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{id}")
    public Response deleteById(@PathVariable Long id){
        try{
            userService.deleted(id);
            return new Response(true,"User with id = " + id + " has been deleted!", null);
        }catch(Exception ex){
            return new Response(false, "Unexpected error!", new ExceptionWrapper(ex));
        }
    }

    @Secured("ROLE_USER")
    @PostMapping("/preChangePassword")
    public Response preChangePassword(Principal principal){
        try{
            User user = userService.getByUsername(principal.getName());
            return new Response(true, "Activation code sent to this email - " + user.getEmail(), userService.preChangePassword(user));
        }catch(Exception ex){
            return new Response(false, "Unexpected error!",new ExceptionWrapper(ex));
        }
    }

    @Secured("ROLE_USER")
    @PostMapping("/changePassword")
    public Response changePassword(Principal principal, @RequestBody PasswordChangeWrapper wrapper){
        try{
            User user = userService.changePassword(wrapper,userService.getByUsername(principal.getName()));
            if(user!=null){
                return new Response(true, "Password successfully changed!", user);
            }else {
                return new Response(false, "Error changing password! It can be because you entered wrong security code!", null);
            }
        }catch(Exception ex){
            return new Response(false, "Unexpected error!", new ExceptionWrapper(ex));
        }
    }

    @Secured("ROLE_USER")
    @GetMapping("/login")
    public Response login(Principal principal){
        try{
            User user = userService.getByUsername(principal.getName());
            if(user!=null){
                return new Response(true, "User login successful!", user, userRoleService.getByUser(user).stream().map(UserRole::getRole).collect(Collectors.toList()));
            }else {
                return new Response(false, "User doesn't exist in database!", null,null);
            }
        }catch(Exception ex){
            return new Response(false, "Unexpected error!", new ExceptionWrapper(ex));
        }
    }
    
    @Secured("ROLE_ADMIN")
    @GetMapping("/getAll")
    public Response getAll(){
        try{
            return new Response(true, "All users in db", userService.getAll());
        }catch(Exception ex){
            return new Response(false, "Unexpected error!", new ExceptionWrapper(ex));
        }
    }
}
