package com.example.demo.model;

public class User {
    private String uid;
    private String firstName;
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
    @Override
    public String toString() {
        return "{ uid: "+uid+", firstName: "+firstName+", lastName: "+lastName+" }";
    }
}
