package com.form.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.form.entity.Employee;

public class FactoryProvider {

	public static SessionFactory factory;
	
	public static SessionFactory getFactory() {
		
		if(factory==null) {
			factory=
            new Configuration().addAnnotatedClass(Employee.class).configure().buildSessionFactory();
		}
		return factory;
	}
	public static void closeFactory() {
		
		if(factory.isOpen()) {
			factory.close();
		}
	}
}
