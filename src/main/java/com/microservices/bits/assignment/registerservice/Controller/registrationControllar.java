package com.microservices.bits.assignment.registerservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.bits.assignment.registerservice.Entity.User;
import com.microservices.bits.assignment.registerservice.userRepo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class registrationControllar {

    @Autowired
    private UserRepo userRepo;


    @PostMapping("/test")
    public  String test(){
        return "registrationService";
    }
    @PostMapping("/register")
    public ResponseEntity<?> doRegister(@RequestBody String body) throws JsonProcessingException {

        Map<String,String> map = new HashMap<String,String>();

        ObjectMapper mapper = new ObjectMapper();

        map = mapper.readValue(body, HashMap.class);

        String mobileno = map.get("mobileno");
        String empid = map.get("empid");
        String emptype = map.get("emptype");
        String empname = map.get("empname");
        String secretkey = map.get("secretkey");

        if(!userRepo.findById(mobileno).isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("User Already exsist");
        }
        // register user if not already there in DB.
       User user =  userRepo.save(User.builder().mobileno(mobileno).
                empname(empname).
                empid(empid).
                emptype(emptype).
                secretkey(secretkey).
                build());

        if(user!=null){
            return ResponseEntity.status(HttpStatus.OK).body("User Registered Successfully");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problem in user registartion");
        }

    }

}
