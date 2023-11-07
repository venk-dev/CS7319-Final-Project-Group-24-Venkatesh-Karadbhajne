package com.venk.org.tutorials.service;

import com.venk.org.tutorials.model.CourseRegistration;
import com.venk.org.tutorials.model.User;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author venkateshkaradbhajne
 */
@Component
public interface CourseRegistrationService {

  public List<CourseRegistration> getCourseRegistrationsByUser(User user);

  public CourseRegistration addCourseRegistrations(CourseRegistration courseRegistration);

}
