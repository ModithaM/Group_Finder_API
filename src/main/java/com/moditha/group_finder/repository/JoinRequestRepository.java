package com.moditha.group_finder.repository;

import com.moditha.group_finder.model.JoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinRequestRepository extends JpaRepository<JoinRequest, Integer> {
    List<JoinRequest> getJoinRequestsByProjectId(int projectId);
}
