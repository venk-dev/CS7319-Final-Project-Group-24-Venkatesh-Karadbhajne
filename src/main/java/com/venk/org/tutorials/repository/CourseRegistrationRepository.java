package com.venk.org.tutorials.repository;

import com.venk.org.tutorials.model.CourseRegistration;
import com.venk.org.tutorials.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author venkateshkaradbhajne
 */
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {

  List<CourseRegistration> findCourseRegistrationByUser(User user);
}
