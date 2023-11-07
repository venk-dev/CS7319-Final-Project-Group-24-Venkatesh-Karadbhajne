package com.venk.org.tutorials.controller;

import com.venk.org.tutorials.model.CourseRegistration;
import com.venk.org.tutorials.model.User;
import com.venk.org.tutorials.service.CourseRegistrationService;
import com.venk.org.tutorials.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author venkateshkaradbhajne
 */
@EnableMethodSecurity
@RestController
@RequestMapping("/api/courseregistrations")
public class CourseRegistrationController {

  @Autowired
  CourseRegistrationService courseRegistrationService;
  @Autowired
  UserService userService;

  @GetMapping
  public ResponseEntity<List<CourseRegistration>> getAllCourseRegistrations(@RequestParam(required = false) String title, HttpServletRequest request) {
    try {
      List<CourseRegistration> courseRegistrations = new ArrayList<CourseRegistration>();
      String userName = request.getUserPrincipal().getName();
      User user = userService.getUserByName(userName);
      courseRegistrations = courseRegistrationService.getCourseRegistrationsByUser(user);

      if (courseRegistrations.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(courseRegistrations, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @PostMapping
  public ResponseEntity<CourseRegistration> addCourseRegistration(@RequestBody CourseRegistration courseRegistration, HttpServletRequest request) {
    try {
      String userName = request.getUserPrincipal().getName();
      User user = userService.getUserByName(userName);
      courseRegistration.setUser(user);
      return new ResponseEntity<>(courseRegistrationService.addCourseRegistrations(courseRegistration), HttpStatus.CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
