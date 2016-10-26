package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

  public static void main(String[] args) {

    //create session factory
    SessionFactory factory = new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Student.class)
                            .buildSessionFactory();
    //create a session
    Session session = factory.getCurrentSession();
    
    //use the session to save the java object
    try{
      //create a student object
      System.out.println("Creating a new student object..");
      Student tempStudent = new Student("Paul", "Wall", "paul@luv2code.com");
      
      //start a transaction
      session.beginTransaction();
      
      //save the student object
      System.out.println("saving the student..");
      session.save(tempStudent);
      
      //commit the transaction
      session.getTransaction().commit();
      
      System.out.println("Done!");
    }
    
    finally{
      factory.close();
    }
  }

}
