package com.moditha.group_finder.repository;

import com.moditha.group_finder.model.ProjectMember;
import com.moditha.group_finder.model.dto.ProjectMemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer> {
    @Query("SELECT new com.moditha.group_finder.model.dto.ProjectMemberDTO(" +
            "u.id, u.firstName, u.lastName, pm.role) " +
            "FROM ProjectMember pm " +
            "JOIN User u ON pm.memberId = u.id " +
            "WHERE pm.projectId = :projectId")
    List<ProjectMemberDTO> findMembersByProjectId(@Param("projectId") int projectId);
}
