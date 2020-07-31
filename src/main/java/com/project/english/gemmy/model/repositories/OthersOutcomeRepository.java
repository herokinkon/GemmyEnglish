package com.project.english.gemmy.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.english.gemmy.model.jpa.OthersOutcome;

@Repository
public interface OthersOutcomeRepository extends JpaRepository<OthersOutcome, Long>{

}
