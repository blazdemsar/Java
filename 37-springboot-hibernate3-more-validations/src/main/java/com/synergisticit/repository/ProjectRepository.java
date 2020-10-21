package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
