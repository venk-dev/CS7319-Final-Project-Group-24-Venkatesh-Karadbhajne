package com.venk.org.tutorials.repository;

import com.venk.org.tutorials.model.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  List<Course> findByTitleContaining(String title);
}
