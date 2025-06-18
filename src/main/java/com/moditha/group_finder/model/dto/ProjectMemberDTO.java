package com.moditha.group_finder.model.dto;

import com.moditha.group_finder.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProjectMemberDTO {
    private int memberId;
    private String firstName;
    private String lastName;
    private String role;

    public ProjectMemberDTO(int memberId, String firstName, String lastName, Role role) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role.name(); // Convert enum to string
    }
}
