package com.moditha.group_finder.model;

import com.moditha.group_finder.model.enums.Status;
import com.moditha.group_finder.model.enums.Technologies;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private String moduleCode;
    private String moduleName;
    private int creatorId;
    private int maxMembers;
    private int currentMembers;
    @Enumerated(EnumType.STRING)
    private Technologies frontendTechnology;
    @Enumerated(EnumType.STRING)
    private Technologies backendTechnology;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}