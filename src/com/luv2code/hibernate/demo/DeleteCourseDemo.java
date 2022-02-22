package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteCourseDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start transaction
			session.beginTransaction();
			
			Course course = session.get(Course.class, 10);
			session.delete(course);
			
			//now john's course list should be empty, yeah?
			Student student = session.get(Student.class, 1);
			System.out.println("Is his course list empty? Let's see... " + student.getCourses());
			
			//our answer is - not yet! You need to commit the transaction before it actually updates the tables!
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}

}
