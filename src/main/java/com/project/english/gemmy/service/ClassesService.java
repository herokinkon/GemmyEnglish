package com.project.english.gemmy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.dto.ClassesInfoDto;
import com.project.english.gemmy.model.jpa.Classes;
import com.project.english.gemmy.model.jpa.Course;
import com.project.english.gemmy.model.repositories.ClassesRepository;
import com.project.english.gemmy.model.repositories.CourseRepository;
import com.project.english.gemmy.model.repositories.StudentInfoRepository;

@Service
public class ClassesService {
	
	@Autowired
	private ClassesRepository classesRepo;
	
	@Autowired
	private StudentInfoRepository studentInfoRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	public List<ClassesInfoDto> getAllClass() {
		List<Classes> allClasses = classesRepo.findAll();
		if (allClasses != null && !allClasses.isEmpty()) {
			List<ClassesInfoDto> result = new ArrayList<>();
			allClasses.stream().forEach(item -> {
				ClassesInfoDto temp = new ClassesInfoDto(item);
				result.add(temp);
			});
			return result;
		}
		return null;
	}
	
	public ClassesInfoDto getClassById(Long id) {
		Optional<Classes> classInfo = classesRepo.findById(id);
		if (classInfo.isPresent()) {
			return new ClassesInfoDto(classInfo.get());
		}
		return null;
	}
	
	public ClassesInfoDto createNewClass(ClassesInfoDto classRequest) {
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
			return new ClassesInfoDto(result);
		}
		return null;
	}
	
	public ClassesInfoDto updateClass(ClassesInfoDto classRequest) {
		Classes beforeUpdate = null;
		Optional<Classes> temp = classesRepo.findById(classRequest.getId());
		if (temp.isPresent()) {
			beforeUpdate = temp.get();
		}
		Classes classInfo = new Classes();
		classInfo.setId(classRequest.getId());
		classInfo.setClassName(classRequest.getClassName() != null ? classRequest.getClassName() : "");
		classInfo.setClassCode(classRequest.getClassCode() != null ? classRequest.getClassCode() : "");
		Optional<Course> course = courseRepo.findById(classRequest.getCourseId());
		if (course.isPresent()) {
			classInfo.setCourse(course.get());
		}
		classInfo.setDescription(classRequest.getDescription());
		classInfo.setEndDate(classRequest.getEndDate());
		classInfo.setFee(classRequest.getFee() != null ? classRequest.getFee() : beforeUpdate.getFee());
		classInfo.setStartDate(classRequest.getStartDate());
		classInfo.setStatus(classRequest.getStatus());
		classInfo.setFeePayments(beforeUpdate.getFeePayments());
		classInfo.setStaffInfos(beforeUpdate.getStaffInfos());
		classInfo.setStudentInfos(beforeUpdate.getStudentInfos());
		Classes result = classesRepo.save(classInfo);
		if (result != null) {
			return new ClassesInfoDto(result);
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
	
	public List<ClassesInfoDto> getClassesByStudent(long studentId){
		return classesRepo.findByStudentInfos_id(studentId);
	}
	
//	public boolean updateClassAndAttendance(ClassAttendance request) {
//		ClassesInfoDto result1 = this.updateClass(request.getClassInfo());
//		boolean result2 = this.updateAttedance(request.getAttendance());
//		if (result1 != null && result2 != null) {
//			return true;
//		}
//	}
//	
//	public boolean updateAttedance(Attendance request) {
//		String json = new Gson().toJson(request);
//		studentInfoRepo.findById(request.get)
//	}
}
