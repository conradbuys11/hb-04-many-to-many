package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

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
			
			InstructorDetail detail = session.get(InstructorDetail.class, 2);
			if(detail != null) {
				System.out.println("Details: " + detail);
				System.out.println("Associated instructor: " + detail.getInstructor());
			}
			
			//commit transaction
			session.getTransaction().commit();
			
		}
		catch(Exception e){
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
