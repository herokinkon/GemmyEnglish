package com.project.english.gemmy.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.english.gemmy.model.dto.Attendance;
import com.project.english.gemmy.model.dto.StudentDTO;
import com.project.english.gemmy.model.jpa.StudentInfo;
import com.project.english.gemmy.model.repositories.StudentInfoRepository;
import com.project.english.gemmy.util.CommonUtils;

@Service
public class StudentInfoService {

	@Autowired
	private StudentInfoRepository studentInfoRepo;

	public List<StudentDTO> getAllStudent() {
		List<StudentInfo> students = studentInfoRepo.findAll();
		if (students != null && !students.isEmpty()) {
			return students.stream().map(StudentDTO::new).collect(Collectors.toList());
		}
		return null;
	}

	public StudentDTO getStudentInfoById(Long id) {
		Optional<StudentInfo> studentInfo = studentInfoRepo.findById(id);
		if (studentInfo.isPresent()) {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.addMappings(new PropertyMap<StudentInfo, StudentDTO>() {
				@Override
				protected void configure() {
					skip(destination.getAttendance());
				}
			});
			StudentDTO result = modelMapper.map(studentInfo.get(), StudentDTO.class);
			return result;
		}
		return null;
	}

	public StudentDTO createNewStudent(StudentDTO updateInfoRequest) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<StudentDTO, StudentInfo>() {
			@Override
			protected void configure() {
				skip(destination.getAttendance());
			}
		});
		StudentInfo studentInfo = modelMapper.map(updateInfoRequest, StudentInfo.class);
		StudentInfo result = studentInfoRepo.save(studentInfo);
		if (result != null) {
			modelMapper.addMappings(new PropertyMap<StudentInfo, StudentDTO>() {
				@Override
				protected void configure() {
					skip(destination.getAttendance());
				}
			});
			return modelMapper.map(result, StudentDTO.class);
		}
		return null;
	}

	// use for user
	public StudentDTO updateStudent(StudentDTO updateInfoRequest) {
		ModelMapper modelMapper = new ModelMapper();
		Optional<StudentInfo> temp = studentInfoRepo.findById(updateInfoRequest.getId());
		if (temp.isPresent()) {
			modelMapper.addMappings(new PropertyMap<StudentDTO, StudentInfo>() {
				@Override
				protected void configure() {
					skip(destination.getAttendance());
				}
			});
			StudentInfo studentInfo = modelMapper.map(updateInfoRequest, StudentInfo.class);
			StudentInfo result = studentInfoRepo.save(studentInfo);
			if (result != null) {
				return new StudentDTO(result);
			}
		}
		return null;
	}

	public void deleteStudent(Long id) {
		Optional<StudentInfo> studentInfo = studentInfoRepo.findById(id);
		if (studentInfo.isPresent()) {
			studentInfoRepo.delete(studentInfo.get());
		}
	}

	public List<StudentDTO> getStudentInfoByClass(Long id) {
		List<StudentInfo> allUserAccount = studentInfoRepo.findAll();
		if (allUserAccount != null && !allUserAccount.isEmpty()) {
			List<StudentDTO> result = new ArrayList<>();
			allUserAccount.stream().forEach(item -> {
				StudentDTO temp = new StudentDTO(item);
				result.add(temp);
			});
			return result;
		}
		return null;
	}

	public List<StudentDTO> getStudentListByClass(long classId) {
		List<StudentInfo> studentInfoList = studentInfoRepo.findByClasses_id(classId);
		List<StudentDTO> result = new ArrayList<>();
		for (StudentInfo stu : studentInfoList) {
			StudentDTO temp = new StudentDTO(stu);
			if (stu.getAttendance() != null && !stu.getAttendance().isEmpty()) {
				Type userListType = new TypeToken<ArrayList<Attendance>>() {
				}.getType();
				List<Attendance> atten = new Gson().fromJson(stu.getAttendance(), userListType);
				String currentDate = CommonUtils.getCurrentDate();
				atten.stream().forEach(ca -> {
					if (ca.getClassId() == classId && ca.getAttendance().containsKey(currentDate)) {
						temp.setAttendance(ca.getAttendance().get(currentDate) == 1 ? true : false);
					}
				});
			}
			result.add(temp);
		}
		return result;
	}

	public List<StudentDTO> getStudentInfoByName(String name) {
		return studentInfoRepo.findByFullNameContains(name);
	}

	public List<StudentDTO> getStudentListByName(String name) {
		return studentInfoRepo.findByFullNameContains(name);
	}

	public List<StudentDTO> getNewStudentList() {
		List<StudentInfo> students = studentInfoRepo.findAll();
		List<StudentInfo> result = students.stream().filter(s -> s.getClasses().isEmpty()).collect(Collectors.toList());
		if (students != null && !students.isEmpty()) {
			return result.stream().map(StudentDTO::new).collect(Collectors.toList());
		}
		return null;
	}
}
