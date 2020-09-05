package com.project.english.gemmy.model.repositories;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.english.gemmy.model.dto.TimelineDTO;
import com.project.english.gemmy.model.jpa.Timeline;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {

	List<TimelineDTO> findByStartBetween(Date startDate, Date endDate);

	List<TimelineDTO> findByStartRecurLessThanAndEndRecurGreaterThan(Date endDate, Date startDate);

	List<TimelineDTO> findByStaff_idIn(Collection<Long> staffIds);

	@Query("SELECT t FROM Timeline t WHERE ((t.start >= :startDate and t.start <= :endDate) or "
			+ "(t.startRecur <= :endDate and t.endRecur >= :startDate)) and " + "(:empty = true or t.staff.id in :ids)")
	List<TimelineDTO> findAllByStaffIdAndWithDateRange(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("empty") boolean isEmpty, @Param("ids") Collection<Long> staffIds);
}
