package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="User")
public class UserEntity {
    
    @Id
    @Column(name="uid")
    private String uid;
    
    @Column(name="firstName")
    private String firstName;
    
    @Column(name="lastName")
    private String lastName;

    public String getUid() {
        return uid;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setUid(String uid) {
        this.uid=uid;
    }
    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }
    public void setLastName(String lastName) {
        this.lastName=lastName;
    }
}
