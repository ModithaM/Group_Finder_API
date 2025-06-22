package com.moditha.group_finder.model.dto;

import com.moditha.group_finder.model.Project;
import lombok.Data;

import java.util.List;

@Data
public class MyProjectsDTO {
    private List<Project> createdProjects;
    private List<Project> joinedProjects;
}
