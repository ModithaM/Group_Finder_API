package com.moditha.group_finder.mappers;

import com.moditha.group_finder.model.Project;
import com.moditha.group_finder.model.dto.BaseProjectDTO;
import com.moditha.group_finder.model.dto.ProjectDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectToBaseprojectDTO {

    public static List<BaseProjectDTO> mapToBaseProjectDTOs(List<Project> projects) {
        return projects.stream()
                .map(ProjectToBaseprojectDTO::mapToBaseProjectDTO)
                .collect(Collectors.toList());
    }

    public static BaseProjectDTO mapToBaseProjectDTO(Project project) {
        return new BaseProjectDTO(
                project.getTitle(),
                project.getDescription(),
                project.getModuleCode(),
                project.getModuleName(),
                project.getFrontendTechnology(),
                project.getBackendTechnology(),
                project.getMaxMembers(),
                project.getStatus(),
                project.getCreatorId()
        );
    }

    public static ProjectDTO mapToProjectDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setModuleCode(project.getModuleCode());
        dto.setModuleName(project.getModuleName());
        dto.setFrontendTechnology(project.getFrontendTechnology());
        dto.setBackendTechnology(project.getBackendTechnology());
        dto.setMaxMembers(project.getMaxMembers());
        dto.setStatus(project.getStatus());
        dto.setCreatorId(project.getCreatorId());
        dto.setProjectMembers(new ArrayList<>()); // Initialize empty list
        return dto;
    }
}
