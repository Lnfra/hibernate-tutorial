package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
      int studentId = 1;      

      //now get a new session and start transaction
      session = factory.getCurrentSession();
      session.beginTransaction();
      
      //retrieve student based on the id: primary key
      System.out.println("\nGetting student with id: " + studentId);
      
      Student myStudent = session.get(Student.class, studentId);

      //delete the student object
      System.out.println("Deleting student: " + myStudent );
      session.delete(myStudent);
      
      //delete student directly from database without first retrieving it
      System.out.println("Deleting student with id: 2");
      session.createQuery("delete from Student where id=2").executeUpdate();
      
      //commit the transaction
      session.getTransaction().commit();
      
      System.out.println("Done!");
    }
    
    finally{
      factory.close();
    }
  }

}
