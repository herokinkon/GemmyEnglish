package com.project.english.gemmy.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.dto.StaffDTO;
import com.project.english.gemmy.model.jpa.StaffInfo;
import com.project.english.gemmy.model.repositories.StaffInfoRepository;

@Service
public class StaffInfoService {

	@Autowired
	private StaffInfoRepository staffInfoRepo;

	public List<StaffDTO> getAllStaff() {
		List<StaffInfo> staffs = staffInfoRepo.findAll();
		return staffs.stream().map(StaffDTO::new).collect(Collectors.toList());
	}

	public StaffDTO getStaffById(long id) {
		Optional<StaffInfo> result = staffInfoRepo.findById(id);
		if (result.isPresent()) {
			return new StaffDTO(result.get());
		}
		throw new EntityNotFoundException(String.format("Staff id does not exist: %s", id));
	}

	public void removeStaff(long id) {
		if (this.getStaffById(id) != null) {
			staffInfoRepo.deleteById(id);
		}
	}

	public void updateStaff(StaffDTO staff) {
		if (staffInfoRepo.existsById(staff.getId())) {
			ModelMapper modelMapper = new ModelMapper();
			StaffInfo staffInfo = modelMapper.map(staff, StaffInfo.class);
			staffInfoRepo.save(staffInfo);
		} else {
			throw new EntityNotFoundException(String.format("Staff is not exist: %s", staff.toString()));
		}
	}

	public StaffDTO createNewStaff(StaffDTO staff) {
		ModelMapper modelMapper = new ModelMapper();
		StaffInfo staffInfo = modelMapper.map(staff, StaffInfo.class);
		return new StaffDTO(staffInfoRepo.save(staffInfo));
	}
	
	public StaffInfo getStaffInfoById(long id) {
		Optional<StaffInfo> result = staffInfoRepo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		throw new EntityNotFoundException(String.format("Staff id does not exist: %s", id));
	}

	public List<StaffDTO> getStaffByNameAndType(String searchText, String type) {
		List<StaffInfo> staffs = staffInfoRepo.findBystaffTypeAndFullNameContains(type, searchText);
		if (!staffs.isEmpty()) {
			return staffs.stream().map(staff -> new StaffDTO(staff.getId(), staff.getFullName()))
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}
	
	public List<StaffDTO> getFullTimeStaffSalary() {
		List<StaffInfo> staffs = staffInfoRepo.findByStaffType("FT");
		return staffs.stream().map(StaffDTO::new).collect(Collectors.toList());
	}
	
	public List<StaffDTO> getPartTimeStaffSalary() {
		List<StaffInfo> staffs = staffInfoRepo.findByStaffType("PT");
		return staffs.stream().map(StaffDTO::new).collect(Collectors.toList());
	}
}
