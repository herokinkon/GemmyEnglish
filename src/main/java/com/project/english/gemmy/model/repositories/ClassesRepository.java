package com.project.english.gemmy.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.english.gemmy.model.jpa.Classes;
import com.project.english.gemmy.model.response.ClassesInfoResponse;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

	List<ClassesInfoResponse> findByStudentInfos_id(long studentId);
}
