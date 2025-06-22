package com.moditha.group_finder.service;

import com.moditha.group_finder.exceptions.ServerErrorException;
import com.moditha.group_finder.model.JoinRequest;
import com.moditha.group_finder.model.dto.JoinRequestDTO;
import com.moditha.group_finder.model.enums.Status;
import com.moditha.group_finder.repository.JoinRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinRequestService {

    @Autowired
    private JoinRequestRepository joinRequestRepository;

    public void createJoinRequest(JoinRequestDTO joinRequestDTO) {
        try {
            JoinRequest joinRequest = new JoinRequest();
            joinRequest.setProjectId(joinRequestDTO.getProjectId());
            joinRequest.setUserId(joinRequestDTO.getUserId());
            joinRequest.setMessage(joinRequestDTO.getMessage());
            joinRequest.setStatus(Status.PENDING);

            joinRequestRepository.save(joinRequest);
        } catch (Exception e) {
            throw new ServerErrorException("Failed to create join request");
        }

    }

    public List<JoinRequest> getJoinRequestsByProjectId(int projectId) {
        try {
            return joinRequestRepository.getJoinRequestsByProjectId(projectId);
        } catch (Exception e) {
            throw new ServerErrorException("Failed to retrieve join requests for project ID: " + projectId);
        }
    }
}
