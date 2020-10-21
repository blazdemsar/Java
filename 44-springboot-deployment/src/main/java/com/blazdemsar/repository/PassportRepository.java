package com.blazdemsar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazdemsar.domain.Passport;

public interface PassportRepository extends JpaRepository<Passport, Long> {

}
