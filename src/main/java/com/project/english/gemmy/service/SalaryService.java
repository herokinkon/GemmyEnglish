package com.project.english.gemmy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.dto.OthersOutcomeDto;
import com.project.english.gemmy.model.jpa.OthersOutcome;
import com.project.english.gemmy.model.jpa.StaffInfo;
import com.project.english.gemmy.model.repositories.OthersOutcomeRepository;

@Service
public class SalaryService {

	@Autowired
	private StaffInfoService staffInfoService;
	
	@Autowired
	private OthersOutcomeRepository otherOutcomeRepo;
	
	public OthersOutcomeDto createUpdateOtherOutcome(OthersOutcomeDto otherOutcomeDto, boolean isNewOutcome) {
		StaffInfo staffInfo = staffInfoService.getStaffInfoById(otherOutcomeDto.getStaffId());
		OthersOutcome otherOutcome = new OthersOutcome();
		otherOutcome.setCost(otherOutcomeDto.getCost());
		otherOutcome.setPurpose(otherOutcomeDto.getPurpose());
		otherOutcome.setStatus(otherOutcomeDto.getStatus());
		otherOutcome.setUsedDate(otherOutcomeDto.getUsedDate());
		otherOutcome.setStaffInfo(staffInfo);
		if (!isNewOutcome) {
			otherOutcome.setId(otherOutcomeDto.getId());
		}
		return new OthersOutcomeDto(otherOutcomeRepo.save(otherOutcome));
	}
	
	public List<OthersOutcomeDto> getAllOthersOutcome() {
		List<OthersOutcome> otherOutcomeList = otherOutcomeRepo.findAll();
		List<OthersOutcomeDto> result = new ArrayList<>();
		otherOutcomeList.stream().forEach(item -> {
			OthersOutcomeDto tmp = new OthersOutcomeDto();
			tmp.setId(item.getId());
			tmp.setCost(item.getCost());
			tmp.setUsedDate(item.getUsedDate());
			tmp.setPurpose(item.getPurpose());
			tmp.setStatus(item.getStatus());
			tmp.setFullName(item.getStaffInfo().getFullName());
			tmp.setBirthday(item.getStaffInfo().getBirthday());
			tmp.setStaffId(item.getStaffInfo().getId());
			result.add(tmp);
		});
		return result;
	}
}
