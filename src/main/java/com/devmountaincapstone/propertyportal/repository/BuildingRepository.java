package com.devmountaincapstone.propertyportal.repository;


import com.devmountaincapstone.propertyportal.entites.Building;
import com.devmountaincapstone.propertyportal.entites.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findByPropertyEquals(Property property);
}
