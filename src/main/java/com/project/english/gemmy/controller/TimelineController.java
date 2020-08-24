package com.project.english.gemmy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.english.gemmy.model.dto.TimelineDTO;
import com.project.english.gemmy.service.TimelineService;

@RestController
@RequestMapping("/api/timeline")
public class TimelineController {

	@Autowired
	private TimelineService timelineService;

	@GetMapping("/")
	public ResponseEntity<List<TimelineDTO>> getAllEvents() {
		List<TimelineDTO> timelineLst = timelineService.getAll();
		if (timelineLst != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(timelineLst);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TimelineDTO> getTimelineById(@PathVariable long id) {
		TimelineDTO result = timelineService.getTimelineById(id);
		if (result != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public void removeTimelineById(@PathVariable long id) {
		timelineService.removeTimeline(id);
	}

	@PutMapping
	public void updateTimeline(@RequestBody TimelineDTO timeline) {
		timelineService.updateTimeline(timeline);
	}

	@PostMapping("/")
	public ResponseEntity<TimelineDTO> createTimeline(@RequestBody TimelineDTO timeline) {
		TimelineDTO newTimeline = timelineService.createNewTimeline(timeline);
		return new ResponseEntity<>(newTimeline, HttpStatus.CREATED);
	}

	@GetMapping("/searchByStaffId")
	public ResponseEntity<List<TimelineDTO>> searchTimeline(@RequestParam List<Long> timelineIds) {
		List<TimelineDTO> timelineLst = timelineService.getTimelineByStaff(timelineIds);
		if (!timelineLst.isEmpty()) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(timelineLst);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
