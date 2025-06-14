package com.moditha.group_finder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * Get current user profile
     *
     * @return User object
     * @apiNote This is a private endpoint. Authentication required to access it.
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        return null;
    }


    /**
     * Update user profile
     *
     * @param profilePicture , lastname , bio, skills, year, Semester, email
     * @return Updated user object.
     * @apiNote This is a private endpoint. Authentication required to access it.
     */
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestParam String profilePicture) {
        return null;
    }

}
