package com.example.demo.service;

import java.util.List;
import com.example.demo.model.User;

public interface UserService {
    public String getHello();
    public List<User> getUserDetails() throws Exception;
    public String insertUserDetails(User user);
    public String validateUserDetails(User user);
}
