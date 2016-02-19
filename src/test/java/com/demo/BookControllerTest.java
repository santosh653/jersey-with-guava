package com.demo;

import com.demo.application.BookApplicationService;
import com.demo.model.Book;
import com.demo.service.BookService;
import com.demo.service.impl.BookServiceImpl;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import java.util.Collection;

/**
 * Created by sanandasena on 2/18/2016.
 */
public class BookControllerTest extends JerseyTest {

    protected Application configure() {
        final BookService bookService = new BookServiceImpl();
        return new BookApplicationService(bookService);
    }

    @Test
    public void getBookTest() {
        Book responseBook = target("books").path("1").request().get(Book.class);
        Assert.assertNotNull(responseBook);
    }

    @Test
    public void getBooksTest() {
        Collection<Book> responseBooks = target("books").request().get(new GenericType<Collection<Book>>() {
        });
        Assert.assertNotNull(responseBooks);
    }

    @Test
    public void testEndPoints() {
        Book responseBook1 = target("books").path("1").request().get(Book.class);
        Book responseBook2 = target("books").path("1").request().get(Book.class);
        Assert.assertEquals(responseBook1.getTitle(), responseBook2.getTitle());
    }
}
