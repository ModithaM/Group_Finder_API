package com.moditha.group_finder.service;

import com.moditha.group_finder.exceptions.ServerErrorException;
import com.moditha.group_finder.model.Project;
import com.moditha.group_finder.model.dto.ProjectDTO;
import com.moditha.group_finder.model.dto.ProjectFilterDTO;
import com.moditha.group_finder.model.enums.Status;
import com.moditha.group_finder.model.enums.Technologies;
import com.moditha.group_finder.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


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
            newProject.setStatus(project.getStatus());
            newProject.setCurrentMembers(0);
            newProject.setFrontendTechnology(project.getFrontendTechnology());
            newProject.setBackendTechnology(project.getBackendTechnology());
            return repository.save(newProject);
        } catch (Exception e) {
            throw new ServerErrorException("Failed to create project: " + e.getMessage());
        }
    }

    public Page<Project> getAllOpenProjects(ProjectFilterDTO filter, Pageable pageable) {
        Specification<Project> spec = buildProjectSpecification(filter);

        return repository.findAll(spec, pageable);
    }

    private Specification<Project> buildProjectSpecification(ProjectFilterDTO filter) {
        return Specification.where(isOpen())
                .and(hasCourseId(filter.getCourseId()))
                .and(hasFrontendTechnology(filter.getFrontendTechnology()))
                .and(hasBackendTechnology(filter.getBackendTechnology()));
    }

    private Specification<Project> isOpen() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), Status.OPEN);
    }

    private Specification<Project> hasCourseId(String courseId) {
        return (root, query, criteriaBuilder) -> {
            if (courseId == null || courseId.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("moduleCode"), courseId);
        };
    }

    private Specification<Project> hasFrontendTechnology(String frontendTech) {
        return (root, query, criteriaBuilder) -> {
            if (frontendTech == null || frontendTech.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            try {
                Technologies tech = Technologies.valueOf(frontendTech.toUpperCase());
                return criteriaBuilder.equal(root.get("frontendTechnology"), tech);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid frontend technology: " + frontendTech);
            }
        };
    }

    private Specification<Project> hasBackendTechnology(String backendTech) {
        return (root, query, criteriaBuilder) -> {
            if (backendTech == null || backendTech.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            try {
                Technologies tech = Technologies.valueOf(backendTech.toUpperCase());
                return criteriaBuilder.equal(root.get("backendTechnology"), tech);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid backend technology: " + backendTech);
            }
        };
    }

}
