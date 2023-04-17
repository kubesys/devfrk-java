package io.github.kubesys.devfrk.spring.data.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.kubesys.devfrk.spring.cores.AbstractHttpHandler;
import io.github.kubesys.devfrk.spring.data.daos.BookDao;
import io.github.kubesys.devfrk.spring.data.models.Book;
import io.github.kubesys.devfrk.tools.annotations.ServiceDefinition;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 * @author Jennifer Reif
 * @author Michael J. Simons
 */
@ServiceDefinition
public class BookService extends AbstractHttpHandler {

	@Autowired
	private BookDao bookDao;

	public List<Book> listBooks() {
		return bookDao.findAll();
	}
}
