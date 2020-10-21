package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.Passport;

public interface PassportRepository extends JpaRepository<Passport, Long> {

}
