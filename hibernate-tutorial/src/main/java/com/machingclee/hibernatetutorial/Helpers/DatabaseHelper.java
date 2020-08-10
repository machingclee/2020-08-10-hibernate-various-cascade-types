package com.machingclee.hibernatetutorial.Helpers;

import java.util.List;

import com.machingclee.hibernatetutorial.Interfaces.IHasToString;
import com.machingclee.hibernatetutorial.models.Course;
import com.machingclee.hibernatetutorial.models.Instructor;
import com.machingclee.hibernatetutorial.models.InstructorDetail;
import com.machingclee.hibernatetutorial.models.Review;
import com.machingclee.hibernatetutorial.models.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DatabaseHelper {

  public static SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
      .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).addAnnotatedClass(Review.class)
      .addAnnotatedClass(Student.class).buildSessionFactory();

  public void save(IHasToString object) {
    Session session = factory.openSession();
    Transaction transaction = session.beginTransaction();
    try {
      System.out.println("saving: " + object.toString());
      session.save(object);
      transaction.commit();
    } catch (Exception err) {
      err.printStackTrace();
    } finally {
      session.close();
    }
  }

  public static void add(IHasToString object) {
    Session session = factory.openSession();
    Transaction transaction = session.beginTransaction();
    try {
      session.save(object);
      transaction.commit();
    } catch (Exception err) {
      err.printStackTrace();
    } finally {
      session.close();
    }
  }

  public static void update(IHasToString object) {
    Session session = factory.openSession();
    Transaction transaction = session.beginTransaction();
    try {
      session.update(object);
      transaction.commit();
    } catch (Exception err) {
      err.printStackTrace();
    } finally {
      session.close();
    }
  }

  public static void delete(IHasToString object) {
    Session session = factory.openSession();
    Transaction transaction = session.beginTransaction();
    try {
      session.delete(object);
      transaction.commit();
    } catch (Exception err) {
      err.printStackTrace();
    } finally {
      session.close();
    }

  }

  public static <T> List<T> query(String queryString, Class<T> theClass) {
    Session session = factory.openSession();
    try {
      List<T> results = session.createQuery(queryString, theClass).list();
      return results;
    } catch (Exception err) {
      err.printStackTrace();
      return null;
    } finally {
      session.close();
    }

  }

}