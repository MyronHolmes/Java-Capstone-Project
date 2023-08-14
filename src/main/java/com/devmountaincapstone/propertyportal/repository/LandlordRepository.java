package com.devmountaincapstone.propertyportal.repository;


import com.devmountaincapstone.propertyportal.entites.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LandlordRepository extends JpaRepository<Landlord, Long> {

    Optional<Landlord> findByUsername(String username);
}
