package com.moditha.group_finder.repository;

import com.moditha.group_finder.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjectRepository extends JpaRepository<Project, Integer>,JpaSpecificationExecutor<Project> {
}
