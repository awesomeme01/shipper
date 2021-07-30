package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.model.UserRole;

import java.util.List;

public interface UserRoleService {
    UserRole create (UserRole userRole);
    UserRole update (UserRole userRole);
    void delete (Long id);
    List<UserRole> getByUser(User user);
    UserRole getById (Long id);

    List<UserRole> getMyUserRoles(User user);
}
