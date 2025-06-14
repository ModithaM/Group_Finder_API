package com.moditha.group_finder.controller;

import com.moditha.group_finder.model.User;
import com.moditha.group_finder.model.dto.LoginRequestDTO;
import com.moditha.group_finder.model.dto.LoginResponseDTO;
import com.moditha.group_finder.model.dto.RegisterRequestDTO;
import com.moditha.group_finder.service.JwtService;
import com.moditha.group_finder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;

    /**
     * Authenticate student and return JWT token with user info.
     *
     * @param loginRequest The request body containing email and password.
     * @return A JWT token and basic user information if authentication succeeds.
     * @apiNote This is a public endpoint. No authentication required to access it.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO loginResponseDTO = null;
        try {
            User authenticatedUser = userService.login(loginRequest);
            String jwtToken = jwtService.generateToken(authenticatedUser);
            loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setToken(jwtToken);
            loginResponseDTO.setUser(authenticatedUser);
            loginResponseDTO.setExpiresIn(jwtService.getExpirationTime());
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Login failed!"));
        }
    }


    /**
     * Register User and Send Success Message
     *
     * @param registerRequest The request body containing all user details.
     * @return Success message if registration succeeded.
     * @apiNote This is a public endpoint. No authentication required to access it.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequest) {
        try {
            if(userService.addUser(registerRequest)) {
                return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "User registered successfully!"));
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Username already exists!"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "User registration failed!"));
        }
    }
}
