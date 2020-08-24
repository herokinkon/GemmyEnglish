package com.project.english.gemmy.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.jpa.Classes;
import com.project.english.gemmy.model.jpa.Exam;
import com.project.english.gemmy.model.jpa.ExamResult;
import com.project.english.gemmy.model.jpa.StudentInfo;
import com.project.english.gemmy.model.repositories.ClassesRepository;
import com.project.english.gemmy.model.repositories.ExamRepository;
import com.project.english.gemmy.model.repositories.ExamResultRepository;

@Service
public class ExamResultService {

	@Autowired
	private ExamResultRepository examResultRepo;
	
	@Autowired
	private ClassesRepository classRepo;
	
	@Autowired
	private ExamRepository examRepo;
	
	public List<ExamResult> getExamResult(Long classId, Long studentId, Long examId) {
		return examResultRepo.findExamResultByStudentClassExam(classId, studentId, examId);
	}
	
	public List<ExamResult> getListResult(Long classId, Long studentId, Long examId) {
		List<ExamResult> result = getExamResult(classId, studentId, examId);
		Exam exam = examRepo.findById(examId).get();
		if (classId != null) {
			Optional<Classes> classes = classRepo.findById(classId);
			if (classes.isPresent()) {
				Set<StudentInfo> studentList =  classes.get().getStudentInfos();
				List<ExamResult> newResult = new ArrayList<>();
				for (StudentInfo student : studentList) {
					boolean exist = false;
					for (ExamResult examResult : result) {
						if (student.getId() == examResult.getStudentInfo().getId() && examId == examResult.getExam().getId()) {
							exist = true;
							break;
						}
					}
					if (!exist) {
						ExamResult temp = new ExamResult();
						temp.setStudentInfo(student);
						temp.setClasses(classes.get());
						temp.setExam(exam);
						newResult.add(temp);
					}
				}
				result.addAll(newResult);
			}
		}
		return result;
	}
	
	public List<ExamResult> updateExamResult(ExamResult[] updateExams) {
		List<ExamResult> temp = Arrays.asList(updateExams);
		Long classId = updateExams[0].getClasses().getId();
		Long examId = updateExams[0].getExam().getId();
		examResultRepo.saveAll(temp);
		return this.getListResult(classId, null, examId);
	}
}
