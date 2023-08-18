package com.devmountaincapstone.propertyportal.entites;

import com.devmountaincapstone.propertyportal.dtos.LandlordDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Landlords")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Landlord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name= "email", unique = true)
    private String email;

    @Column(name= "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "landlord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Property> propertySet = new HashSet<>();

    public Landlord(LandlordDto landlordDto){
        if (landlordDto.getFirstName() != null){
            this.firstName = landlordDto.getFirstName();
        }
        if (landlordDto.getLastName() != null){
            this.lastName = landlordDto.getLastName();
        }
        if (landlordDto.getEmail() != null) {
            this.email = landlordDto.getEmail();
        }
        if (landlordDto.getUsername() != null){
            this.username = landlordDto.getUsername();
        }
        if (landlordDto.getPassword() != null){
            this.password = landlordDto.getPassword();
        }
    }


}
