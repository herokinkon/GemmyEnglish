package com.project.english.gemmy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.jpa.Classes;
import com.project.english.gemmy.model.jpa.Course;
import com.project.english.gemmy.model.repositories.ClassesRepository;
import com.project.english.gemmy.model.repositories.CourseRepository;
import com.project.english.gemmy.model.request.ClassRequest;

@Service
public class ClassesService {
	
	@Autowired
	private ClassesRepository classesRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	public List<Classes> getAllClass() {
		List<Classes> allClasses = classesRepo.findAll();
		if (allClasses != null && !allClasses.isEmpty()) {
			return allClasses;
		}
		return null;
	}
	
	public Classes getClassById(Long id) {
		Optional<Classes> classInfo = classesRepo.findById(id);
		if (classInfo.isPresent()) {
			return classInfo.get();
		}
		return null;
	}
	
	public Classes createNewClass(ClassRequest classRequest) {
		Classes classInfo = new Classes();
		classInfo.setClassName(classRequest.getClassName());
		classInfo.setClassCode(classRequest.getClassCode());
		if (classRequest.getCourseId() != null) {
			Optional<Course> course = courseRepo.findById(classRequest.getCourseId());
			if (course.isPresent()) {
				classInfo.setCourse(course.get());
			}
		}
		classInfo.setDescription(classRequest.getDescription());
		classInfo.setEndDate(classRequest.getEndDate());
		classInfo.setFee(classRequest.getFee());
		classInfo.setStartDate(classRequest.getStartDate());
		classInfo.setStatus(classRequest.getStatus());
		Classes result = classesRepo.save(classInfo);
		if (result != null) {
			return result;
		}
		return null;
	}
	
	public Classes updateClass(ClassRequest classRequest) {
		Classes beforeUpdate = null;
		Optional<Classes> temp = classesRepo.findById(classRequest.getId());
		if (temp.isPresent()) {
			beforeUpdate = temp.get();
		}
		Classes classInfo = new Classes();
		classInfo.setId(beforeUpdate.getId());
		classInfo.setClassName(classRequest.getClassName() != null ? classRequest.getClassName() : beforeUpdate.getClassName());
		classInfo.setClassCode(classRequest.getClassCode() != null ? classRequest.getClassCode() : beforeUpdate.getClassCode());
		if (classRequest.getCourseId() == beforeUpdate.getId()) {
			classInfo.setCourse(beforeUpdate.getCourse());
		} else {
			Optional<Course> course = courseRepo.findById(classRequest.getCourseId());
			if (course.isPresent()) {
				classInfo.setCourse(course.get());
			}
		}
		classInfo.setDescription(classRequest.getDescription() != null ? classRequest.getDescription() : beforeUpdate.getDescription());
		classInfo.setEndDate(classRequest.getEndDate() != null ? classRequest.getEndDate() : beforeUpdate.getEndDate());
		classInfo.setFee(classRequest.getFee() != null ? classRequest.getFee() : beforeUpdate.getFee());
		classInfo.setStartDate(beforeUpdate.getStartDate());
		classInfo.setStatus(classRequest.getStatus() != null ? classRequest.getStatus() : beforeUpdate.getStatus());
		classInfo.setFeePayments(beforeUpdate.getFeePayments());
		classInfo.setStaffInfos(beforeUpdate.getStaffInfos());
		classInfo.setStudentInfos(beforeUpdate.getStudentInfos());
		Classes result = classesRepo.save(classInfo);
		if (result != null) {
			return result;
		}
		return null;
	}
	
	public boolean deleteClass(Long id) {
		Optional<Classes> classInfo = classesRepo.findById(id);
		if (classInfo.isPresent()) {
			try {
				classesRepo.delete(classInfo.get());
				return true;
			} catch (Exception e){
				return false;
			}
		}
		return false;
	}
	
}
