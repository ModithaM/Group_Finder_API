package com.moditha.group_finder.repository;

import com.moditha.group_finder.model.Project;
import com.moditha.group_finder.model.ProjectMember;
import com.moditha.group_finder.model.dto.ProjectMemberDTO;
import com.moditha.group_finder.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer> {
    @Query("SELECT new com.moditha.group_finder.model.dto.ProjectMemberDTO(" +
            "u.id, u.firstName, u.lastName, pm.role) " +
            "FROM ProjectMember pm " +
            "JOIN User u ON pm.memberId = u.id " +
            "WHERE pm.projectId = :projectId")
    List<ProjectMemberDTO> findMembersByProjectId(@Param("projectId") int projectId);

    // Find all projects where the user is a member
    @Query(value = "SELECT p.* FROM project p " +
            "JOIN project_member pm ON p.id = pm.project_id " +
            "WHERE pm.member_id = :userId AND pm.role = 'MEMBER'",
            nativeQuery = true)
    List<Project> findJoinedProjectsByUserIdAndRole(@Param("userId") int userId);

    @Transactional
    void deleteByProjectId(int projectId);
}
