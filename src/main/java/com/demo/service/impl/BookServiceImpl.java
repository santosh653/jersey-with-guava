package com.demo.service.impl;

import com.demo.dao.BookDao;
import com.demo.dao.impl.BookDaoImpl;
import com.demo.model.Book;
import com.demo.service.BookService;
import com.google.common.util.concurrent.ListenableFuture;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * Created by sanandasena on 2/18/2016.
 */
public class BookServiceImpl implements BookService {
    private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);

    private BookDao bookDao;

    public BookServiceImpl() {
        bookDao = new BookDaoImpl();
    }

    @Override
    public ListenableFuture<Collection<Book>> getBooksAsync() {
        LOGGER.info("Entered getBooksAsync()");
        return bookDao.getBooksAsync();
    }

    @Override
    public ListenableFuture<Book> getBookAsync(String id) {
        LOGGER.info("Entered getBookAsync(" + id + ")");
        return bookDao.getBookAsync(id);
    }

    @Override
    public ListenableFuture<Book> addBookAsync(Book book) {
        LOGGER.info("Entered addBookAsync(" + book + ")");
        return bookDao.addBookAsync(book);
    }
}
