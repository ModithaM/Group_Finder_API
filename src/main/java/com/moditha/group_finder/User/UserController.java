package com.moditha.group_finder.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user/auth")
public class UserController {
    @Autowired
    UserService userService;

    //Login Validation
    //if user available return user data if not return error
    @PostMapping ("/login")
    public @ResponseBody ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        Optional<User> user = userService.findUser(body.get("username"), body.get("password"));
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
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
