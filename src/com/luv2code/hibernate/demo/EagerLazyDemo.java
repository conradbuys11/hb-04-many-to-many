package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

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

			//get instructor
			Instructor instructor = session.get(Instructor.class, 1);
			
			System.out.println("breakpoint baby");
			
			//commit transaction
			session.getTransaction().commit();
			
			//closing early to see what happens with lazy loading
			session.close();
			
			//errors!
			System.out.println(instructor.getCourses());
			
		} finally {
			factory.close();
		}
	}

}
