package com.demo.dao.impl;

import com.demo.dao.BookDao;
import com.demo.exception.BookNotFoundException;
import com.demo.model.Book;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * Created by sanandasena on 2/18/2016.
 */
public class BookDaoImpl implements BookDao {
    private static final Logger LOGGER = Logger.getLogger(BookDaoImpl.class);

    private Map<String, Book> books;
    private ListeningExecutorService listeningExecutorService;

    public BookDaoImpl() {
        books = new ConcurrentHashMap<>();
        listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    }

    private Collection<Book> getBooks() throws BookNotFoundException {
        LOGGER.info("Entered getBooks()");
        return (books.values());
    }

    public ListenableFuture<Collection<Book>> getBooksAsync() {
        LOGGER.info("Entered getBooksAsync()");
        return (listeningExecutorService.submit(this::getBooks));
    }

    private Book getBook(String id) throws BookNotFoundException{
        LOGGER.info("Entered getBook(" + id + ")");
        if (books.containsKey(id)) {
            return (books.get(id));
        } else {
            throw new BookNotFoundException("Book " + id + " is not found");
        }
    }

    public ListenableFuture<Book> getBookAsync(final String id) {
        LOGGER.info("Entered getBookAsync(" + id + ")");
        return (listeningExecutorService.submit(() -> getBook(id)));
    }

    private Book addBook(Book book) {
        LOGGER.info("Entered addBook(" + book + ")");
        book.setId(UUID.randomUUID().toString());
        books.put(book.getId(), book);
        return (book);
    }

    public ListenableFuture<Book> addBookAsync(final Book book) {
        LOGGER.info("Entered addBookAsync(" + book + ")");
        return (listeningExecutorService.submit(() -> addBook(book)));
    }
}
