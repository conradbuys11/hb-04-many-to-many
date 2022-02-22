package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

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

			//create course
			Course course = new Course("Being a Cool Guy 101");
			session.save(course);
			
			//gonna make some students and add them
			Student one = new Student("John", "Doe", "john@luv2code.com");
			Student two = new Student("Mary", "Public", "mary@luv2code.com");
			course.addStudent(one);
			course.addStudent(two);
			
			//make sure to save the students! it used to just auto do that but i guess not?
			//is it because we saved our course earlier?
			session.save(one);
			session.save(two);
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}

}
