package com.blazdemsar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazdemsar.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
