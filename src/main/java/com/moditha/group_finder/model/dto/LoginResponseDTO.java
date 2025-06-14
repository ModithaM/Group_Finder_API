package com.moditha.group_finder.model.dto;

import com.moditha.group_finder.model.User;
import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private long expiresIn;
    private User user;
}
