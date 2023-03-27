package com.example.demo.dao;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import com.example.demo.model.User;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public class UserDAOImpl implements UserDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getUserDetails(){
        Query q = entityManager.createQuery("SELECT u FROM UserEntity u");
        List<UserEntity> userEntityList = q.getResultList();
        List<User> userList = new ArrayList<>();
        if (userEntityList.isEmpty())
            return userList;
        for (UserEntity ue: userEntityList) {
            User user = new User();
            user.setUid(ue.getUid());
            user.setFirstName(ue.getFirstName());
            user.setLastName(ue.getLastName());
            userList.add(user);
        }
        return userList;
    }
    public String insertUserDetails(User user){
        UserEntity ue = new UserEntity();
        ue.setUid(user.getUid());
        ue.setFirstName(user.getFirstName());
        ue.setLastName(user.getLastName());
        entityManager.persist(ue);
        return "\"Insertion Successful\"";
    }
    public String validateUserDetails(User user){
        UserEntity ue = entityManager.find(UserEntity.class,user.getUid());
        if (ue!=null){
            if (ue.getFirstName().equalsIgnoreCase(user.getFirstName()) && ue.getLastName().equalsIgnoreCase(user.getLastName()))
                return "\"Validation Successful\"";
        }
        return "\"Validation Failed\"";
    }
}
