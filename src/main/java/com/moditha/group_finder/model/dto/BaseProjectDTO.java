package com.moditha.group_finder.model.dto;

import com.moditha.group_finder.model.enums.Status;
import com.moditha.group_finder.model.enums.Technologies;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseProjectDTO {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    @NotBlank(message = "Module Code is required")
    private String moduleCode;
    private String moduleName;
    @NotNull(message = "Frontend Technology is required")
    private Technologies frontendTechnology;
    @NotNull(message = "Backend Technology is required")
    private Technologies backendTechnology;
    private int maxMembers;
    private Status status;
}
