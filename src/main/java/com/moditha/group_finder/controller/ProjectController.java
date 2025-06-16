package com.moditha.group_finder.controller;

import com.moditha.group_finder.exceptions.ServerErrorException;
import com.moditha.group_finder.model.dto.ProjectDTO;
import com.moditha.group_finder.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    /**
     * Create a new project.
     *
     * @param project details of the project to be created.
     * @return Project object if creation is successful, or an error message if it fails.
     * @apiNote This is a private endpoint. No authentication required to access it.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectDTO project) {
        try {
            return ResponseEntity.ok(projectService.createProject(project));
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Project Creation failed due to an internal error"));
        }
    }
}
