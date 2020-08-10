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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.machingclee.hibernatetutorial.Interfaces.IHasToString;

@Entity
@Table(name = "courses")
public class Course implements IHasToString {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "title")
  private String title;

  @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
  @JoinColumn(name = "insturctor_id")
  private Instructor instructor;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @Column(name = "reviews")
  private List<Review> reviews;

  @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
      CascadeType.REFRESH })
  @JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
  private List<Student> students;

  public Course() {

  }

  public Course(String title) {
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Instructor getInstructor() {
    return this.instructor;
  }

  public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void addReview(Review review) {
    if (this.reviews == null)
      this.reviews = new ArrayList<>();

    this.reviews.add(review);
    review.setCourse(this);
  }

  public List<Review> getReviews() {
    return this.reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public List<Student> getStudents() {
    return this.students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public void addStudent(Student student) {
    if (this.students == null)
      this.students = new ArrayList<Student>();

    this.students.add(student);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", title='" + getTitle() + "'" + ", instructor='" + getInstructor() + "'"
        + ", reviews='" + getReviews() + "'" + ", students='" + getStudents() + "'" + "}";
  }

}