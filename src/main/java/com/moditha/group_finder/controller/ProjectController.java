package com.moditha.group_finder.controller;

import com.moditha.group_finder.exceptions.ServerErrorException;
import com.moditha.group_finder.model.Project;
import com.moditha.group_finder.model.dto.BaseProjectDTO;
import com.moditha.group_finder.model.dto.ProjectDTO;
import com.moditha.group_finder.model.dto.ProjectFilterDTO;
import com.moditha.group_finder.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
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
     * @apiNote This is a private endpoint. authentication required to access it.
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

    /**
     * Get all open projects with filtering.
     *
     * @param courseId ,skills , specialization to filter projects.
     * @return Paginated list of projects.
     * @apiNote This is a private endpoint. authentication required to access it.
     */
    @GetMapping
    public ResponseEntity<?> getAllOpenProjects(
            @RequestParam(required = false) String courseId,
            @RequestParam(required = false) String frontendTechnology,
            @RequestParam(required = false) String backendTechnology,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size ) {

        try {
            ProjectFilterDTO filter = ProjectFilterDTO.builder()
                    .courseId(courseId)
                    .frontendTechnology(frontendTechnology)
                    .backendTechnology(backendTechnology)
                    .build();

            Pageable pageable = PageRequest.of(page, size);
            Page<Project> projects = projectService.getAllOpenProjects(filter, pageable);

            return ResponseEntity.ok(projects);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while fetching projects"));
        }
    }


    /**
     * Get projects by id.
     *
     * @param id of the project to be fetched.
     * @return Project with members.
     * @apiNote This is a private endpoint. authentication required to access it.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable int id) {
        try {
            ProjectDTO project = projectService.getProjectById(id);
            return ResponseEntity.ok(project);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while fetching the project"));
        }
    }

    /**
     * Update a project by id.
     *
     * @param project details to update the project.
     * @return Updated Project object if successful, or an error message if it fails.
     * @apiNote This is a private endpoint. authentication required to access it.
     */
    @PutMapping("/{id}/user/{uid}")
    public ResponseEntity<?> updateProject(@PathVariable int id, @PathVariable int uid,@Valid @RequestBody BaseProjectDTO project) {
        try {
            return ResponseEntity.ok().body(projectService.updateProjectById(id, uid, project));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while updating the project"));
        }
    }

    /**
     * Delete a project by id.
     *
     * @param id of the project to be deleted.
     * @return Success message if deletion is successful, or an error message if it fails.
     * @apiNote This is a private endpoint. authentication required to access it.
     */
    @DeleteMapping("/{id}/user/{uid}")
    public ResponseEntity<?> deleteProject(@PathVariable int id, @PathVariable int uid) {
        try {
            projectService.deleteProjectById(id, uid);
            return ResponseEntity.ok(Map.of("message", "Project deleted successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while deleting the project"));
        }
    }
}
