package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRole create(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public UserRole update(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public void delete(Long id) {
    userRoleRepository.deleteById(id);
    }

    @Override
    public List<UserRole> getByUser(User user) {
        return userRoleRepository.getByEmail(user.getEmail());
    }

    @Override
    public UserRole getById(Long id) {
        return userRoleRepository.findById(id).get();
    }

    @Override
    public List<UserRole> getMyUserRoles(User user) {
        return userRoleRepository.findAll().stream().filter(x->x.getUser().equals(user)).collect(Collectors.toList());
    }
}
