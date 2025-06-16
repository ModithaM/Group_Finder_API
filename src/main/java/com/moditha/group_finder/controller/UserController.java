package com.moditha.group_finder.controller;

import com.moditha.group_finder.exceptions.ServerErrorException;
import com.moditha.group_finder.exceptions.UserNotFoundException;
import com.moditha.group_finder.model.dto.RegisterRequestDTO;
import com.moditha.group_finder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * Update user profile
     *
     * @param updateRequest , lastname , bio, skills, year, Semester, email etc...
     * @return if success returns Updated user object.
     * @apiNote This is a private endpoint. Authentication required to access it.
     */
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody RegisterRequestDTO updateRequest) {
        try{
            return ResponseEntity.ok(userService.updateProfile(updateRequest));
        }
        catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "User not found"));
        }
        catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Profile update failed due to an internal error"));
        }
    }
}
