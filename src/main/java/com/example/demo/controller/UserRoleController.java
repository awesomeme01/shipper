package com.example.demo.controller;

import com.example.demo.helper.Response;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.service.UserRoleService;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/userRole")
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UserService userService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/create/{id}")
    public Response create(@PathVariable Long id, UserRole userRole){
        try{
            userRole.setUser(userService.getById(id));
            return new Response(true, "Role for user created!", userRoleService.create(userRole));
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/update")
    public Response update(UserRole userRole){
        try{
            return new Response(true, "UserRole successfully updated!", userRoleService.update(userRole));
        }
        catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/getMyUserRoles")
    public List<UserRole> getMyUserRoles(Principal principal){
        User user = userService.getByUsername(principal.getName());
        return userRoleService.getMyUserRoles(user);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/getById")
    public UserRole getById(Long id){
        return userRoleService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById (@PathVariable Long id){
        userRoleService.delete(id);
    }
}

