package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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

			//HQL querying time
			Query<Instructor> query = session.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id = :theInstructorId", Instructor.class);
			
			//setting the parameter that we defined in that above query ( :theInstructorId )
			query.setParameter("theInstructorId", 1);
			
			//ok time to execute query
			//since we did a join fetch, we get the courses with it, basically manual eager loading
			Instructor instructor = query.getSingleResult();
			
			session.getTransaction().commit();
			session.close();
			
			System.out.println("Instructor info: " + instructor);
			
		} finally {
			factory.close();
		}
	}

}
