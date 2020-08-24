package com.project.english.gemmy.model.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.english.gemmy.model.dto.TimelineDTO;
import com.project.english.gemmy.model.jpa.Timeline;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {

	List<TimelineDTO> findByStaff_idIn(Collection<Long> staffIds);
}
