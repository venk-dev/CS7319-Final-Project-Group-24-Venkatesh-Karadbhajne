package com.venk.org.tutorials.service.impl;

import com.venk.org.tutorials.repository.CourseRepository;
import com.venk.org.tutorials.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author venkateshkaradbhajne
 */
@Service
public class CourseServiceImpl implements CourseService {

  @Autowired
  CourseRepository courseRepository;
}
