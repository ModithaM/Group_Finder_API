package com.moditha.group_finder.service;

import com.moditha.group_finder.exceptions.ServerErrorException;
import com.moditha.group_finder.model.Project;
import com.moditha.group_finder.model.dto.ProjectDTO;
import com.moditha.group_finder.model.dto.ProjectFilterDTO;
import com.moditha.group_finder.model.dto.ProjectMemberDTO;
import com.moditha.group_finder.model.enums.Role;
import com.moditha.group_finder.model.enums.Status;
import com.moditha.group_finder.model.enums.Technologies;
import com.moditha.group_finder.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectService {
    @Autowired
    ProjectRepository repository;
    @Autowired
    ProjectMemberService projectMemberService;

    //create new project
    public Project createProject(ProjectDTO project) {
        try {
            //TODO: use mapstruct to convert DTO to entity
            //add to project entity
            Project newProject = new Project();
            newProject.setTitle(project.getTitle());
            newProject.setDescription(project.getDescription());
            newProject.setModuleCode(project.getModuleCode());
            newProject.setModuleName(project.getModuleName());
            newProject.setCreatorId(project.getCreatorId());
            newProject.setMaxMembers(project.getMaxMembers());
            newProject.setStatus(project.getStatus());
            newProject.setCurrentMembers(1); // creator is the first member
            newProject.setFrontendTechnology(project.getFrontendTechnology());
            newProject.setBackendTechnology(project.getBackendTechnology());
            Project savedProject = repository.save(newProject);
            //add creator as a project member
            projectMemberService.addProjectMember(savedProject.getId(), project.getCreatorId(), Role.LEADER);
            return savedProject;
        } catch (Exception e) {
            throw new ServerErrorException("Failed to create project: " + e.getMessage());
        }
    }

    //filter and get all open projects
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
                Technologies tech = Technologies.valueOf(frontendTech);
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
                Technologies tech = Technologies.valueOf(backendTech);
                return criteriaBuilder.equal(root.get("backendTechnology"), tech);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid backend technology: " + backendTech);
            }
        };
    }

    //get project by id
    public ProjectDTO getProjectById(int id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setTitle(project.getTitle());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setModuleCode(project.getModuleCode());
        projectDTO.setModuleName(project.getModuleName());
        projectDTO.setCreatorId(project.getCreatorId());
        projectDTO.setMaxMembers(project.getMaxMembers());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setFrontendTechnology(project.getFrontendTechnology());
        projectDTO.setBackendTechnology(project.getBackendTechnology());

        // Fetch and set project members
        List<ProjectMemberDTO> members = projectMemberService.getProjectMembersByProjectId(id);
        projectDTO.setProjectMembers(members);

        return projectDTO;
    }


}
