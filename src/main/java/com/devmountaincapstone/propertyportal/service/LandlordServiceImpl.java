package com.devmountaincapstone.propertyportal.service;


import com.devmountaincapstone.propertyportal.dtos.LandlordDto;
import com.devmountaincapstone.propertyportal.dtos.UnitDto;
import com.devmountaincapstone.propertyportal.entites.Landlord;
import com.devmountaincapstone.propertyportal.entites.Unit;
import com.devmountaincapstone.propertyportal.repository.LandlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LandlordServiceImpl implements LandlordService {
    @Autowired
    private LandlordRepository landlordRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addLandlord(LandlordDto landlordDto){
        List<String> response = new ArrayList<>();
        Landlord landlord = new Landlord(landlordDto);
        landlordRepository.saveAndFlush(landlord);
        response.add("http://localhost:8080/login.html");
        return response;
    }


    @Override
    public List<String> landLordLogin(LandlordDto landlordDto){
        List<String> response = new ArrayList<>();
        Optional<Landlord> landlordOptional = landlordRepository.findByUsername(landlordDto.getUsername());

        if (landlordOptional.isPresent()){
            if (passwordEncoder.matches(landlordDto.getPassword(), landlordOptional.get().getPassword())){
                response.add("Hello " + landlordOptional.get().getFirstName() + ", Welcome To Property Portal.");
                response.add(String.valueOf(landlordOptional.get().getId()));
            }else {
                response.add("Username or password incorrect");
            }
        }else {
            response.add("Username or password incorrect");
        }

        return response;
    }
}
