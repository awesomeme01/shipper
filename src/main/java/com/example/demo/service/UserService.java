package com.example.demo.service;


import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    User create (User user);
    User emailActivation(User user);
    User update (User user);
    void deleted (Long id);
    List<User> getMyUsers();
    User getById (Long id);
}
