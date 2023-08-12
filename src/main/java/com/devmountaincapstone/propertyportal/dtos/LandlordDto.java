package com.devmountaincapstone.propertyportal.dtos;

import com.devmountaincapstone.propertyportal.entites.Landlord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandlordDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Set<PropertyDto> propertyDtoSet = new HashSet<>();

    public LandlordDto(Landlord landlord){
        if (landlord.getId() != null){
            this.id = landlord.getId();
        }
        if (landlord.getFirstName() != null){
            this.firstName = landlord.getFirstName();
        }
        if (landlord.getLastName() != null){
            this.lastName = landlord.getFirstName();
        }
        if (landlord.getEmail() != null) {
            this.email = landlord.getEmail();
        }
        if (landlord.getUsername() != null){
            this.username = landlord.getUsername();
        }
        if (landlord.getPassword() != null){
            this.password = landlord.getPassword();
        }
    }
}
