package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start transaction
			session.beginTransaction();
			
//			//create objs & associate them
//			Instructor instructorOne = new Instructor("Chad", "Darby", "darby@luv2code.com");
//			InstructorDetail detailOne = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!");
//			instructorOne.setInstructorDetail(detailOne);
//			
//			//save instructor
//			//cascade all means instructordetail is also saved
//			System.out.println("Saving " + instructorOne);
//			session.save(instructorOne);
			
			//create objs & associate them
			Instructor instructorOne = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
			InstructorDetail detailOne = new InstructorDetail("http://youtube.com", "Guitar");
			instructorOne.setInstructorDetail(detailOne);
			
			//save instructor
			//cascade all means instructordetail is also saved
			System.out.println("Saving " + instructorOne);
			session.save(instructorOne);
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
