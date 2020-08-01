package com.project.english.gemmy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.english.gemmy.model.dto.ClassesInfoDto;
import com.project.english.gemmy.model.dto.FeePaymentDTO;
import com.project.english.gemmy.model.dto.StudentDTO;
import com.project.english.gemmy.model.jpa.FeePayment;
import com.project.english.gemmy.model.repositories.FeePaymentRepository;

@Service
public class FeePaymentService {

	@Autowired
	private FeePaymentRepository paymentRepo;
	@Autowired
	private StudentInfoService studentService;
	@Autowired
	private ClassesService classService;

	public void createNewPayment(FeePaymentDTO payment) {
		ClassesInfoDto classinfo = payment.getClasses();
		classinfo.getClass();
		ModelMapper modelMapper = new ModelMapper();
		paymentRepo.save(modelMapper.map(payment, FeePayment.class));
	}

	public List<Map<String, String>> getPaymentInClass(long classId) {
		List<StudentDTO> lstStudent = studentService.getStudentListByClass(classId);
		List<FeePaymentDTO> payments = paymentRepo.findByClasses_id(classId);
		return lstStudent.stream().map(stu -> getStudentPayment(stu, payments)).collect(Collectors.toList());
	}

	public Map<String, String> getPaymentForStudent(long studentId) {
		Map<String, String> paymentInfo = null;
		List<FeePaymentDTO> payments = paymentRepo.findByStudentInfo_idAndClasses_status(studentId, true);
		if (!payments.isEmpty()) {
			paymentInfo = this.getClassInfo(payments.get(0).getClasses());
			int months = payments.stream().map(pay -> pay.getMonth())
					.collect(Collectors.summingInt(Byte::toUnsignedInt));
			paymentInfo.put("months", String.valueOf(months));
		} else {
			ClassesInfoDto classInfo = classService.getActiveClassByStudentId(studentId);
			if (classInfo != null) {
				paymentInfo = this.getClassInfo(payments.get(0).getClasses());
				paymentInfo.put("months", "0");
				paymentInfo.put("fee", classInfo.getFee());
			}
		}

		return paymentInfo;
	}

	private Map<String, String> getStudentPayment(StudentDTO student, List<FeePaymentDTO> payments) {
		Map<String, String> studentInfo = new HashMap<>();
		studentInfo.put("studentId", student.getId().toString());
		studentInfo.put("fullName", student.getFullName());
		studentInfo.put("birthday", student.getBirthday().toString());
		int months = payments.stream().mapToInt(pay -> getPaidMonthsForStudent(pay, student)).sum();

		studentInfo.put("months", String.valueOf(months));
		return studentInfo;
	}

	private int getPaidMonthsForStudent(FeePaymentDTO payment, StudentDTO student) {
		if (payment.getStudentInfo().getId() == student.getId()) {
			return payment.getMonth();
		}

		return 0;
	}

	private Map<String, String> getClassInfo(ClassesInfoDto clazz) {
		Map<String, String> classInfo = new HashMap<>();
		classInfo.put("classId", clazz.getId().toString());
		classInfo.put("classCode", clazz.getClassCode());
		classInfo.put("className", clazz.getClassName());
		classInfo.put("startDate", clazz.getStartDate().toString());
		classInfo.put("endDate", clazz.getEndDate().toString());
		classInfo.put("fee", clazz.getFee());
		return classInfo;
	}

	public List<Map<String, Object>> getAll() {
		return paymentRepo.findByClasses_status();
	}

	public void removePayment(long id) {
		paymentRepo.deleteById(id);
	}

	public void updatePayment(FeePaymentDTO payment) {
		if (paymentRepo.existsById(payment.getId())) {
			ModelMapper modelMapper = new ModelMapper();
			FeePayment paymentInfo = modelMapper.map(payment, FeePayment.class);
			paymentRepo.save(paymentInfo);
		} else {
			throw new EntityNotFoundException(String.format("Payment is not exist: %s", payment.toString()));
		}
	}

	public FeePaymentDTO getPayment(long id) {
		Optional<FeePayment> payment = paymentRepo.findById(id);
		if (payment.isPresent()) {
			return new FeePaymentDTO(payment.get());
		}
		return null;
	}

//	public int getAvailableMonth(long studentId, long classId) {
//		List<FeePaymentDTO> payments = paymentRepo.findByStudentInfo_idAndClasses_id(studentId, classId);
//
//	}
}
