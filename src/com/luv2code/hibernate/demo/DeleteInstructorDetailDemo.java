package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
				//if our entity doesn't have cascading delete, we remove relationship in other obj first
				detail.getInstructor().setInstructorDetail(null);
				
				session.delete(detail);
			}
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
