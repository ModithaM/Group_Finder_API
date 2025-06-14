package com.moditha.group_finder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
