package com.project.english.gemmy.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.english.gemmy.model.dto.ClassesInfoDto;
import com.project.english.gemmy.model.jpa.Classes;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

	List<ClassesInfoDto> findByStudentInfos_id(long studentId);
}
