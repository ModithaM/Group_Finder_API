package com.moditha.group_finder.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String specialization;
    private int year;
    private int semester;
    private String bio;
    private List<String> skills;
    private String github;
    private String linkedin;
    private String profilePicture;
}
