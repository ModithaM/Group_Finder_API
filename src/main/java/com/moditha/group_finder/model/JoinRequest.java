package com.moditha.group_finder.model;

import com.moditha.group_finder.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime respondedAt;
}
