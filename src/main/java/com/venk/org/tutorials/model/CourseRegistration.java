package com.venk.org.tutorials.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author venkateshkaradbhajne
 */
@Entity
@Table(name = "CourseRegistration")
public class CourseRegistration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "userId", referencedColumnName = "id")
  private User user;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "courseId", referencedColumnName = "id")
  private Course course;

  public CourseRegistration() {
  }

  public long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }
}
