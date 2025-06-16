package com.moditha.group_finder.service;

import com.moditha.group_finder.exceptions.ServerErrorException;
import com.moditha.group_finder.model.Project;
import com.moditha.group_finder.model.dto.ProjectDTO;
import com.moditha.group_finder.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository repository;

    public Project createProject(ProjectDTO project) {
        try {
            //TODO: use mapstruct to convert DTO to entity
            Project newProject = new Project();
            newProject.setTitle(project.getTitle());
            newProject.setDescription(project.getDescription());
            newProject.setModuleCode(project.getModuleCode());
            newProject.setModuleName(project.getModuleName());
            newProject.setCreatorId(project.getCreatorId());
            newProject.setMaxMembers(project.getMaxMembers());
            newProject.setRequiredSkills(project.getRequiredSkills());
            newProject.setStatus(project.getStatus());
            newProject.setCurrentMembers(0);
            return repository.save(newProject);
        } catch (Exception e) {
            throw new ServerErrorException("Failed to create project: " + e.getMessage());
        }
    }

}
