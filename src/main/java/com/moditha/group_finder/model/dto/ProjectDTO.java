package com.moditha.group_finder.model.dto;

import com.moditha.group_finder.model.enums.Status;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    @NotBlank(message = "Module Code is required")
    private String moduleCode;
    private String moduleName;
    @NotNull(message = "CreatorID is required")
    private int creatorId;
    private int maxMembers;
    private List<String> requiredSkills;
    private Status status;
}
