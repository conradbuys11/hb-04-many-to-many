package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start transaction
			session.beginTransaction();

			//get instructor & create some courses for them
			Instructor instructor = session.get(Instructor.class, 1);
			Course courseOne = new Course("Guitaring");
			Course courseTwo = new Course("History of Plastic");
			instructor.addCourse(courseOne);
			instructor.addCourse(courseTwo);
			session.save(courseOne);
			session.save(courseTwo);
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}

}
