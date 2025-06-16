package com.moditha.group_finder.repository;

import com.moditha.group_finder.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
