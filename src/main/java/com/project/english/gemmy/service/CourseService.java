package com.project.english.gemmy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.jpa.Course;
import com.project.english.gemmy.model.repositories.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepo;

	public List<Course> getAllCourses() {
		List<Course> allCourses = courseRepo.findAll();
		if (allCourses != null && !allCourses.isEmpty()) {
			return allCourses;
		}
		return null;
	}
}
