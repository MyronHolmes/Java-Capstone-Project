package com.devmountaincapstone.propertyportal.service;


import com.devmountaincapstone.propertyportal.dtos.LandlordDto;
import com.devmountaincapstone.propertyportal.repository.LandlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LandlordServiceImpl {
    @Autowired
    private LandlordRepository landlordRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

}
