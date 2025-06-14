package com.moditha.group_finder.model;

import com.moditha.group_finder.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int projectId;
    private int userId;
    private String message;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime respondedAt;
}
