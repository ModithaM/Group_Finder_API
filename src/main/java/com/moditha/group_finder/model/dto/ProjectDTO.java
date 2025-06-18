package com.moditha.group_finder.model.dto;

import com.moditha.group_finder.model.enums.Status;
import com.moditha.group_finder.model.enums.Technologies;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProjectDTO {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    @NotBlank(message = "Module Code is required")
    private String moduleCode;
    private String moduleName;
    private Technologies frontendTechnology;
    private Technologies backendTechnology;
    @NotNull(message = "CreatorID is required")
    private int creatorId;
    private int maxMembers;
    private Status status;
}
