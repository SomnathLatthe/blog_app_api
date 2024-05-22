package com.somnath.blogappapis.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.somnath.blogappapis.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4,message = "Name must be minimum of 4 character !")
    private String name;
    @Email(message = "Email is not valid !")
    private String email;
    @NotEmpty
    @Size(min = 4,max = 8,message = "Password must be min 4 chars or max of 8 chars !")
    private String password;
    @NotEmpty
    private String about;

    private Set<RoleDto> roleSet=new HashSet<>();
}
