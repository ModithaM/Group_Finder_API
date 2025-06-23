package com.moditha.group_finder.controller;

import com.moditha.group_finder.model.dto.JoinRequestDTO;
import com.moditha.group_finder.service.JoinRequestService;
import com.moditha.group_finder.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/member")
public class JoinRequestController {
    @Autowired
    private JoinRequestService joinRequestService;
    @Autowired
    private ProjectMemberService projectMemberService;

    /**
     * Create a new join request.
     *
     * @param joinRequestDTO details of the join request to be created.
     * @return ResponseEntity with a success message.
     * @apiNote This is a private endpoint. Authentication required to access it.
     */
    @PostMapping("/join-request")
    public ResponseEntity<?> createJoinRequest(@RequestBody JoinRequestDTO joinRequestDTO) {
        try {
            joinRequestService.createJoinRequest(joinRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Project Request created successfully!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Project Creation failed due to an internal error"));
        }
    }

    /**
     * Get all join requests for a specific project.
     *
     * @param projectId the ID of the project to get join requests for.
     * @return ResponseEntity with a list of join requests.
     * @apiNote This is a private endpoint. Authentication required to access it.
     */
    @GetMapping("/requests/{projectId}")
    public ResponseEntity<?> getJoinRequests(@PathVariable int projectId) {
        try {
            return ResponseEntity.ok(joinRequestService.getJoinRequestsByProjectId(projectId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to retrieve join requests"));
        }
    }

    /**
     * Update the status of a join request.
     *
     * @param requestId the ID of the join request to update.
     * @param status    the new status of the join request (e.g., "APPROVED", "REJECTED").
     * @return ResponseEntity with a success message.
     * @apiNote This is a private endpoint. Authentication required to access it.
     */
    @PutMapping("/requests/{requestId}")
    public ResponseEntity<?> updateJoinRequestStatus(@PathVariable int requestId, @RequestParam String status) {
        try {
            //status values: "APPROVED", "REJECTED"
            joinRequestService.updateJoinRequestStatus(requestId, status);
            return ResponseEntity.ok(Map.of("message", "Join request status updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to update join request status"));
        }
    }

    /**
     * Leave a project.
     *
     * @param memberId  the ID of the member leaving the project.
     * @param projectId the ID of the project to leave.
     * @return ResponseEntity with a success message.
     * @apiNote This is a private endpoint. Authentication required to access it.
     */
    @DeleteMapping("/{id}/leave/{pid}")
    public ResponseEntity<?> leaveProject(@PathVariable("id") int memberId, @PathVariable("pid") int projectId) {
        try {
            projectMemberService.deleteProjectMember(projectId, memberId);
            return ResponseEntity.ok(Map.of("message", "Left the project successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to leave the project"));
        }
    }
}
