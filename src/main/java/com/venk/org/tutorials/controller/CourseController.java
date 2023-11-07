package com.venk.org.tutorials.controller;

import com.venk.org.tutorials.model.Course;
import com.venk.org.tutorials.repository.CourseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableMethodSecurity
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	CourseRepository courseRepository;

	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses(@RequestParam(required = false) String title, HttpServletRequest request) {
		try {
			List<Course> courses = new ArrayList<Course>();
			if (title == null)
				courseRepository.findAll().forEach(courses::add);
			else
				courseRepository.findByTitleContaining(title).forEach(courses::add);

			if (courses.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(courses, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") long id) {
		Optional<Course> tutorialData = courseRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {
		try {
			Course _course = courseRepository
					.save(course);
			return new ResponseEntity<>(_course, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course course) {
		Optional<Course> tutorialData = courseRepository.findById(id);

		if (tutorialData.isPresent()) {
			Course _course = tutorialData.get();
			_course.setTitle(course.getTitle());
			_course.setDescription(course.getDescription());
			return new ResponseEntity<>(courseRepository.save(_course), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") long id) {
		try {
			courseRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
