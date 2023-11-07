package com.venk.org.tutorials.service.impl;

import com.venk.org.tutorials.model.BillingInvoice;
import com.venk.org.tutorials.model.CourseRegistration;
import com.venk.org.tutorials.model.User;
import com.venk.org.tutorials.repository.BillingInvoiceRepository;
import com.venk.org.tutorials.repository.CourseRegistrationRepository;
import com.venk.org.tutorials.service.CourseRegistrationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author venkateshkaradbhajne
 */
@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

  @Autowired
  CourseRegistrationRepository courseRegistrationRepository;
  @Autowired
  BillingInvoiceRepository billingInvoiceRepository;

  public List<CourseRegistration> getCourseRegistrationsByUser(User user) {
    return courseRegistrationRepository.findCourseRegistrationByUser(user);
  }

  public CourseRegistration addCourseRegistrations(CourseRegistration courseRegistration) {
    BillingInvoice billingInvoice = new BillingInvoice();
    billingInvoice.setStatus(BillingInvoice.StatusEnum.pending);
    billingInvoice.setCourse(courseRegistration.getCourse());
    billingInvoice.setUser(courseRegistration.getUser());
    billingInvoiceRepository.save(billingInvoice);
    return courseRegistrationRepository.save(courseRegistration);
  }
}
