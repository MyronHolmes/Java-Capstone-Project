package com.devmountaincapstone.propertyportal.controllers;


import com.devmountaincapstone.propertyportal.dtos.LandlordDto;
import com.devmountaincapstone.propertyportal.service.LandlordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/landlords")
public class LandlordController {
    @Autowired
    private LandlordService landlordService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public List<String> addUser(@RequestBody LandlordDto landlordDto){
        String passHash = passwordEncoder.encode(landlordDto.getPassword());
        landlordDto.setPassword(passHash);
        return landlordService.addLandlord(landlordDto);
    }

    @PostMapping("/login")
    public List<String> landlordLogin(@RequestBody LandlordDto landlordDto){
        return landlordService.landLordLogin(landlordDto);
    }
}
