package com.devmountaincapstone.propertyportal.service;

import com.devmountaincapstone.propertyportal.dtos.LandlordDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LandlordService {
    @Transactional
    List<String> addLandlord(LandlordDto landlordDto);

    List<String> landLordLogin(LandlordDto landlordDto);
}
