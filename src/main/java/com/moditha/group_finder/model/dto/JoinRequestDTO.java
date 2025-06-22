package com.moditha.group_finder.model.dto;

import lombok.Data;

@Data
public class JoinRequestDTO {
    private int projectId;
    private int userId;
    private String message;
    private String status;
}
