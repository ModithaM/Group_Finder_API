package com.moditha.group_finder.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user/auth")
public class UserController {
    @Autowired
    UserService userService;

    private Map<String, String> sessionStore = new HashMap<>(); // local session store

    //Login Validation
    @PostMapping ("/login")
    public @ResponseBody ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        Optional<User> user = userService.findUser(body.get("username"));
        if (user.isPresent() && user.get().getPassword().equals(body.get("password"))) {
            String token = UUID.randomUUID().toString(); // generate unique token
            sessionStore.put(token, body.get("username"));
            LoginResponse loginResponse = new LoginResponse("Success", token, user.get().getFirstname(), user.get().getLastname(), user.get().getEmail(), user.get().getRole());
            return ResponseEntity.ok(loginResponse);
        } else if (user.isPresent() && !user.get().getPassword().equals(body.get("password"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid password"));
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "User not found"));
        }
    }

    //not tested yet
    @PostMapping("/logout")
    public @ResponseBody ResponseEntity<?> logout(@RequestHeader("session-id") String sessionId) {
        sessionStore.remove(sessionId);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<?> register(@RequestBody User user) {
        try {
            // Save the user
            userService.saveUser(user);
            return ResponseEntity.ok("registered successfully");
        } catch (DataIntegrityViolationException e) {
            // Handle cases like duplicate username/email
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or email already exists");
        } catch (Exception e) {
            // Handle other unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration");
        }
    }
}
