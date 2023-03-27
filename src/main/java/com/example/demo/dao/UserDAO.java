package com.example.demo.dao;

import java.util.List;
import com.example.demo.model.User;

public interface UserDAO {

    public List<User> getUserDetails();
    public String insertUserDetails(User user);
    public String validateUserDetails(User user);
}
