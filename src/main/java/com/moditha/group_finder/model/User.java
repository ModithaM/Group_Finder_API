package com.moditha.group_finder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false , unique = true )
    private String username;
    @Column(nullable = false )
    private String password;
    private String firstName;
    private String lastName;
    @Column(nullable = false , unique = true )
    private String email;
    private String phone;
    private String facebookLink;
    private String githubLink;
    private String linkedinLink;
    private String profilePicture;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ColumnDefault("true")
    private Boolean isActive;
}
