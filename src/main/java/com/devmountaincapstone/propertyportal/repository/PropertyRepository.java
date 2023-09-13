package com.devmountaincapstone.propertyportal.repository;


import com.devmountaincapstone.propertyportal.entites.Landlord;
import com.devmountaincapstone.propertyportal.entites.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByLandlordEquals(Landlord landlord);


}
