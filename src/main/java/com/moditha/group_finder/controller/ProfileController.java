package com.moditha.group_finder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<?> getAllProfiles() {
        return ResponseEntity.ok().body(new ArrayList<>());
    }

    @GetMapping("/search")
    public @ResponseBody ResponseEntity<?> getSearchProfiles(@RequestParam String search) {
        return ResponseEntity.ok().body(new ArrayList<>());
    }
}
