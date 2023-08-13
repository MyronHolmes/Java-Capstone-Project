package com.devmountaincapstone.propertyportal.repository;


import com.devmountaincapstone.propertyportal.entites.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
