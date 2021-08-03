package com.example.demo.service;


import com.example.demo.helper.PasswordChangeWrapper;
import com.example.demo.model.User;

public interface UserService {
    User create (User user);
    User emailActivation(User user, String activationCode);
    User update (User user);
    void deleted (Long id);
    User getByUsername(String username);
    User getById (Long id);
    User preChangePassword(User user);
    User changePassword(PasswordChangeWrapper p, User user);
}
