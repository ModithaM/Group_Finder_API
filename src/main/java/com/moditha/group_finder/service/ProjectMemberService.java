package com.moditha.group_finder.service;

import com.moditha.group_finder.exceptions.ServerErrorException;
import com.moditha.group_finder.model.ProjectMember;
import com.moditha.group_finder.model.dto.ProjectMemberDTO;
import com.moditha.group_finder.model.enums.Role;
import com.moditha.group_finder.repository.ProjectMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberService {
    @Autowired
    ProjectMemberRepository projectMemberRepository;

    public void addProjectMember(int projectId, int memberId, Role role) {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProjectId(projectId);
        projectMember.setMemberId(memberId);
        projectMember.setRole(role);
        try {
            projectMemberRepository.save(projectMember);
        } catch (Exception e) {
            throw new ServerErrorException("Failed to add project member: " + e.getMessage());
        }
    }

    public List<ProjectMemberDTO> getProjectMembersByProjectId(int projectId) {
        try {
            return projectMemberRepository.findMembersByProjectId(projectId);
        } catch (Exception e) {
            throw new ServerErrorException("Failed to retrieve project members: " + e.getMessage());
        }
    }

    public void deleteProjectMembersByProjectId(int projectId) {
        try {
            projectMemberRepository.deleteByProjectId(projectId);
        } catch (Exception e) {
            throw new ServerErrorException("Failed to delete project members: " + e.getMessage());
        }
    }
}
