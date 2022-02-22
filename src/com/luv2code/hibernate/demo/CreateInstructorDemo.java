package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
			
			//create objs & associate them
			Instructor instructorOne = new Instructor("Bob", "Bobberson", "bob@luv2code.com");
			InstructorDetail detailOne = new InstructorDetail("http://youtube.com", "Video Games");
			instructorOne.setInstructorDetail(detailOne);
			
			//save instructor
			//cascade all means instructordetail is also saved
			System.out.println("Saving " + instructorOne);
			session.save(instructorOne);
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}

}
