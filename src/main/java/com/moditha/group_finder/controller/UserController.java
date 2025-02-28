package com.moditha.group_finder.controller;

import com.moditha.group_finder.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PutMapping("/profile")
    public @ResponseBody ResponseEntity<?> updateProfile(@RequestBody User user) {
        return ResponseEntity.ok(user);
    }

    @PutMapping("/visibility")
    public @ResponseBody ResponseEntity<?> updateVisibility(@RequestBody User user) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public @ResponseBody ResponseEntity<?> deleteUser(@RequestBody User user) {
        return ResponseEntity.ok("Deleted");
    }
}
