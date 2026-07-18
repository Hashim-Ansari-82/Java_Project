package com.library.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.library.entity.Book;

public class FactoryProvider {

	 public static SessionFactory factory;
	 
	 public static SessionFactory getFactory() {
		 if(factory == null) {
			 factory=new Configuration().configure().addAnnotatedClass(Book.class).buildSessionFactory();
		 }
		 return factory;
	 }
	 public static void closeFactory() {
		 if(factory.isOpen()) {
			 factory.close();
		 }
	 }
}
