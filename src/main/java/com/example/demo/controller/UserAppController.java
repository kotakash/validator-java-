package com.example.demo.controller;



import java.util.List;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserAppController {
    private static final Logger logger = LoggerFactory.getLogger(UserAppController.class);

    @Autowired
    private UserService userService;
    @Autowired
    public Environment env;

    @RequestMapping(value="/hello",method=RequestMethod.GET)
    public String getHello(){
        logger.debug("Request: GET Path: /hello");
        String s = userService.getHello();
        logger.debug("Response Body: "+s);
        return s;
    }

    @RequestMapping(value="/users",method=RequestMethod.GET)
    public ResponseEntity<List<User>> getUserDetails() throws Exception{
        ResponseEntity<List<User>> response = null;
        logger.debug("Request: GET Path: /users");
        try {
            List<User> users = userService.getUserDetails();
            response = new ResponseEntity<List<User>>(users,HttpStatus.OK);
            logger.debug("Status: "+response.getStatusCodeValue()+"Response Body: "+response.getBody().toString());
        } catch (Exception e){
            String errorMsg = env.getProperty(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errorMsg,e);
        }
        return response;
    }    

    @PostMapping(value="/users",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertUserDetails(@RequestBody User user) throws Exception{
        ResponseEntity<String> response = null;
        logger.debug("Request: POST Path: /users Request Body: "+user.toString());
        try{
            String s = userService.insertUserDetails(user);
            response = new ResponseEntity<String>(s,HttpStatus.CREATED);
            logger.debug("Status: "+response.getStatusCodeValue()+"Response Body: "+response.getBody());
        } catch(Exception e){
            String errorMsg = env.getProperty(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errorMsg,e);
        }
        return response;
    }

    @PostMapping(value="/validate",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> validateUserDetails(@RequestBody User user) throws Exception{
        ResponseEntity<String> response = null;
        logger.debug("Request: POST Path: /validate Request Body: "+user.toString());
        try{
            String s = userService.validateUserDetails(user);
            if ( s.contains("Failed"))
                response = new ResponseEntity<String>(s,HttpStatus.BAD_REQUEST);
            else
                response = new ResponseEntity<String>(s,HttpStatus.CREATED);
            logger.debug("Status: "+response.getStatusCodeValue()+"Response Body: "+response.getBody());
        } catch(Exception e){
            String errorMsg = env.getProperty(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errorMsg,e);
        }
        return response;
    }

}
