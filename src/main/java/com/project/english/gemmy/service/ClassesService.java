package com.project.english.gemmy.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.english.gemmy.model.dto.Attendance;
import com.project.english.gemmy.model.dto.ClassesInfoDto;
import com.project.english.gemmy.model.dto.StudentDTO;
import com.project.english.gemmy.model.dto.UpdateClassAttendanceRequest;
import com.project.english.gemmy.model.jpa.Classes;
import com.project.english.gemmy.model.jpa.Course;
import com.project.english.gemmy.model.jpa.StudentInfo;
import com.project.english.gemmy.model.repositories.ClassesRepository;
import com.project.english.gemmy.model.repositories.CourseRepository;
import com.project.english.gemmy.model.repositories.StudentInfoRepository;
import com.project.english.gemmy.util.CommonUtils;

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
	
	public boolean updateClassAndAttendance(UpdateClassAttendanceRequest request) {
		ClassesInfoDto result1 = this.updateClass(request.getClassInfo());
		boolean result2 = this.updateAttedance(request.getClassInfo().getId(), request.getStudentInfo());
		if (result1 != null && result2) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateAttedance(Long classId, StudentDTO[] studentList) {
		try {
			for (StudentDTO studentDTO : studentList) {
				StudentInfo studentInfo = studentInfoRepo.getOne(studentDTO.getId());
				String currentDate = CommonUtils.getCurrentDate();
				Integer attStatus = studentDTO.getAttendance() ? 1 : 0;
				if (studentInfo.getAttendance() == null || studentInfo.getAttendance().isEmpty()) {
					List<Attendance> attList = new ArrayList<>();
					Attendance tmp = new Attendance();
					Map<String, Integer> attMap = new HashMap<>();
					attMap.put(currentDate, attStatus);
					tmp.setAttendance(attMap);
					tmp.setClassId(classId);
					attList.add(tmp);
					studentInfo.setAttendance(new Gson().toJson(attList));
				} else {
					Type userListType = new TypeToken<ArrayList<Attendance>>() {}.getType();
					List<Attendance> atten = new Gson().fromJson(studentInfo.getAttendance(), userListType);
					atten.stream().forEach(ca -> {
						if (ca.getClassId() == classId) {
							ca.getAttendance().put(currentDate, attStatus);
						}
					});
					studentInfo.setAttendance(new Gson().toJson(atten));
				}
				studentInfoRepo.save(studentInfo);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
