package com.machingclee.hibernatetutorial.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.machingclee.hibernatetutorial.Interfaces.IHasToString;

@Entity
@Table(name = "instructors")
public class Instructor implements IHasToString {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int Id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "instructor_detail_id")
  private InstructorDetail instructorDetail;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor", cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST, CascadeType.REFRESH })
  private List<Course> courses;

  public Instructor() {

  }

  public List<Course> getCourses() {
    return this.courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  public void addCourse(Course course) {
    if (this.courses == null)
      this.courses = new ArrayList<Course>();

    this.courses.add(course);
    course.setInstructor(this);
  };

  public Instructor(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public int getId() {
    return this.Id;
  }

  public void setId(int Id) {
    this.Id = Id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public InstructorDetail getInstructorDetail() {
    return this.instructorDetail;
  }

  public void setInstructorDetail(InstructorDetail instructorDetail) {
    this.instructorDetail = instructorDetail;
  }

  @Override
  public String toString() {
    return "{" + " Id='" + getId() + "'" + ", firstName='" + getFirstName() + "'" + ", lastName='" + getLastName() + "'"
        + ", email='" + getEmail() + "'" + ", instructorDetail='" + getInstructorDetail() + "'" + "}";
  }

}
