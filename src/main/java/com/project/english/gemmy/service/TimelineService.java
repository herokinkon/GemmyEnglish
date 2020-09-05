package com.project.english.gemmy.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.dto.TimelineDTO;
import com.project.english.gemmy.model.jpa.Timeline;
import com.project.english.gemmy.model.repositories.TimelineRepository;

@Service
public class TimelineService {

	@Autowired
	private TimelineRepository timelineRepo;

	public TimelineDTO getTimelineById(long id) {
		Optional<Timeline> result = timelineRepo.findById(id);
		if (result.isPresent()) {
			return new TimelineDTO(result.get());
		}
		throw new EntityNotFoundException(String.format("Timeline id does not exist: %s", id));
	}

	public void removeTimeline(long id) {
		if (this.getTimelineById(id) != null) {
			timelineRepo.deleteById(id);
		}
	}

	public void updateTimeline(TimelineDTO timeline) {
		if (timelineRepo.existsById(timeline.getId())) {
			ModelMapper modelMapper = new ModelMapper();
			Timeline timelineInfo = modelMapper.map(timeline, Timeline.class);
			timelineRepo.save(timelineInfo);
		} else {
			throw new EntityNotFoundException(String.format("Timeline is not exist: %s", timeline.toString()));
		}
	}

	public TimelineDTO createNewTimeline(TimelineDTO timeline) {
		ModelMapper modelMapper = new ModelMapper();
		Timeline timelineInfo = modelMapper.map(timeline, Timeline.class);
		return new TimelineDTO(timelineRepo.save(timelineInfo));
	}

	public List<TimelineDTO> getTimelineByStaffAndDateRange(List<Long> ids, Date startDate, Date endDate) {
		return timelineRepo.findAllByStaffIdAndWithDateRange(startDate, endDate, ids.isEmpty(), ids);
	}
}
