package com.example.demo.service;

import java.util.List;
import com.example.demo.dao.UserDAO;
import com.example.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    public String getHello(){
        return "Hello World!";
    }
    public List<User> getUserDetails() throws Exception {
        List<User> userList = userDAO.getUserDetails();
        if (userList.isEmpty()){
            throw new Exception("Service.NO_USER_FOUND");
        }
        return userList;
    }
    public String insertUserDetails(User user) {
        String s = userDAO.insertUserDetails(user);
        if (s==null)
            return "Service.Insertion not successful";
        return s;
    }
    public String validateUserDetails(User user){
        String s = userDAO.validateUserDetails(user);
        if (s.equals("\"Validation Failed\""))
            return "Service.Validation Failed";
        return s;
    }
}
