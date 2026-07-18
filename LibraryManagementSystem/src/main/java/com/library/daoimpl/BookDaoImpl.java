package com.library.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.library.dao.BookDao;
import com.library.entity.Book;
import com.library.helper.FactoryProvider;

public class BookDaoImpl implements BookDao {

	@Override
	public void saveBook(Book book) {
		SessionFactory factory = FactoryProvider.getFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.persist(book);

		tx.commit();
		session.close();
	}

	@Override
	public Book updateBook(Book book) {
		SessionFactory factory = FactoryProvider.getFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		tx.commit();
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean deleteBook(Integer id) {

		SessionFactory factory = FactoryProvider.getFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		Book book = session.get(Book.class, id);
		if (book != null) {
			session.delete(book);
		}
		tx.commit();
		session.close();
		return false;
	}

	@Override
	public Book getBookById(Integer id) {

		return null;
	}

	@Override
	public List<Book> getAllBooks() {
		SessionFactory factory = FactoryProvider.getFactory();
		Session session = factory.openSession();

		List<Book> list = session.createQuery("from Book", Book.class).getResultList();

		session.close();

		return list;
	}

	@Override
	public List<Book> searchBook(String name) {

		return null;
	}

}
