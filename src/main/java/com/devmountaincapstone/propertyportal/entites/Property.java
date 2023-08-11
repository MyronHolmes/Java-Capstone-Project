package com.devmountaincapstone.propertyportal.entites;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Landlord landlord;
    private String propertyName;
    private static BigDecimal projectedEarnings = new BigDecimal("00.00");

}
