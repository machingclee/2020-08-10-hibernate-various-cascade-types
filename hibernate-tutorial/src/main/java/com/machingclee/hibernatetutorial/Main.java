package com.machingclee.hibernatetutorial;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.machingclee.hibernatetutorial.Helpers.DatabaseHelper;
import com.machingclee.hibernatetutorial.models.Course;
import com.machingclee.hibernatetutorial.models.Instructor;
import com.machingclee.hibernatetutorial.models.Review;
import com.machingclee.hibernatetutorial.models.Student;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
  public static void main(String[] args) {

    Session session = DatabaseHelper.factory.openSession();
    Transaction transaction = session.beginTransaction();

    String queryString = "FROM Course c WHERE c.instructor.firstName=:firstName " + "AND c.title LIKE :title";
    TypedQuery<Course> query = session.createQuery(queryString, Course.class);

    Student student1 = new Student("Tom", "Boy", "tomboy@ff14.com");
    Student student2 = new Student("Lam", "Cheng", "lamcheng@ff14.com");
    Student student3 = new Student("Happy", "Friday", "happyfriday@ff14.com");

    query.setParameter("firstName", "James");
    query.setParameter("title", "Mathematical Analysis");

    Course mathAnalysisCourse = query.getSingleResult();
    mathAnalysisCourse.addStudent(student1);
    mathAnalysisCourse.addStudent(student2);
    mathAnalysisCourse.addStudent(student3);
    session.save(student1);
    session.save(student2);
    session.save(student3);

    // session.save(mathAnalysisCourse);

    // String queryString = "FROM Course c WHERE c.title='Mathematical Analysis'";
    // TypedQuery<Course> query = session.createQuery(queryString, Course.class);
    // Course course = query.getSingleResult();

    // for (Student student : course.getStudents()) {
    // session.delete(student);
    // }

    transaction.commit();
    session.close();
  }
}