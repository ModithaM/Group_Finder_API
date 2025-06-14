package com.moditha.group_finder.controller;

import com.moditha.group_finder.model.dto.LoginRequest;
import com.moditha.group_finder.model.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /**
     * Authenticate student and return JWT token with user info.
     *
     * @param loginRequest The request body containing email and password.
     * @return A JWT token and basic user information if authentication succeeds.
     * @apiNote This is a public endpoint. No authentication required to access it.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = null;
        return ResponseEntity.ok(response);
    }
}
