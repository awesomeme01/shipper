package com.example.demo.controller;

import com.example.demo.helper.ActivationCodeWrapper;
import com.example.demo.helper.Response;
import com.example.demo.model.User;

import com.example.demo.model.UserRole;
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
    @PostMapping("/register")
    public Response create (@RequestBody User user){
        try{
            return new Response(true, "New User created!", userService.create(user));
        }catch(Exception ex){
            return new Response(false, "Unexpected error", ex.getMessage());
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
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }

    @Secured("ROLE_USER")
    @PostMapping("/update")
    public Response update(@RequestBody User user){
        try{
            return new Response(true, "Updated existing user", userService.update(user),
                    userRoleService.getByUser(user).stream().map(UserRole::getRole).collect(Collectors.toList()));
        }catch (Exception ex){
            return new Response(true, "Unexpected error", ex.getMessage());
        }
    }
    @Secured("ROLE_USER")
    @GetMapping("/getMyUser")
    public Response getMyUser(Principal principal){
        try{
            return new Response(true, "Current user information", userService.getByUsername(principal.getName()));
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/getById/{id}")
    public Response getById(@PathVariable Long id){
        try{
            return new Response(true, "User with id = " + id, userService.getById(id));
        }catch(Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{id}")
    public Response deleteById(@PathVariable Long id){
        try{
            userService.deleted(id);
            return new Response(true,"User with id = " + id + " has been deleted!", null);
        }catch(Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }

}
