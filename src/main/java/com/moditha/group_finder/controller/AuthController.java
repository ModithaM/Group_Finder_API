package com.moditha.group_finder.controller;

import com.moditha.group_finder.model.request.LoginRequest;
import com.moditha.group_finder.model.request.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok().build();
    }
}
