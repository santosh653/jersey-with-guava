package com.demo.endpoint;

import com.demo.model.Book;
import com.demo.service.BookService;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ManagedAsync;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.*;
import java.util.Collection;

/**
 * Created by sanandasena on 2/18/2016.
 */
@Path("books")
public class BookController {
    private static final Logger LOGGER = Logger.getLogger(BookController.class);

    @Context
    private BookService bookService;

    @Context
    private Request request;

    @GET
    @Produces({"application/json;qs=1", "application/xml;qs=0.5"})
    @ManagedAsync
    public void getBooks(@Suspended final AsyncResponse response) {
        LOGGER.info("Entered getBooks()");
        ListenableFuture<Collection<Book>> booksFuture = bookService.getBooksAsync();
        Futures.addCallback(booksFuture, new FutureCallback<Collection<Book>>() {
            public void onSuccess(Collection<Book> books) {
                response.resume(books);
            }

            public void onFailure(Throwable thrown) {
                response.resume(thrown);
            }
        });
    }

    @Path("/{id}")
    @GET
    @Produces({"application/json;qs=1", "application/xml;qs=0.5"})
    @ManagedAsync
    public void getBook(@PathParam("id") String id, @Suspended final AsyncResponse response) {
        LOGGER.info("Entered getBook(" + id + ")");
        ListenableFuture<Book> bookFuture = bookService.getBookAsync(id);
        Futures.addCallback(bookFuture, new FutureCallback<Book>() {
            public void onSuccess(Book book) {
                EntityTag entityTag = generateEntityTag(book);
                Response.ResponseBuilder rb = request.evaluatePreconditions(entityTag);
                if (rb != null) {
                    response.resume(rb.build());
                } else {
                    response.resume(Response.ok().tag(entityTag).entity(book).build());
                }
            }

            public void onFailure(Throwable thrown) {
                response.resume(thrown);
            }
        });
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ManagedAsync
    public void addBook(Book book, @Suspended final AsyncResponse response) {
        LOGGER.info("Entered addBook(" + book + ")");
        ListenableFuture<Book> bookFuture = bookService.addBookAsync(book);
        Futures.addCallback(bookFuture, new FutureCallback<Book>() {
            public void onSuccess(Book addedBook) {
                response.resume(addedBook);
            }

            public void onFailure(Throwable thrown) {
                LOGGER.error(thrown.getMessage());
                response.resume(thrown);
            }
        });
    }

    private EntityTag generateEntityTag(Book book) {
        return (new EntityTag(DigestUtils.md5Hex(book.getAuthor() +
                book.getTitle() + book.getPublished() + book.getExtras())));
    }
}
