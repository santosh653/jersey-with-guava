package com.demo.message.body.writer;

import com.demo.model.Book;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;


/**
 * Created by sanandasena on 2/19/2016.
 */

@Provider
@Produces(MediaType.APPLICATION_XML)
public class BookMessageBodyWriter implements MessageBodyWriter<Collection<Book>> {

    @JacksonXmlRootElement(localName = "books")
    public class BooksWrapper {

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "book")
        public Collection<Book> books;

        BooksWrapper(Collection<Book> books) {
            this.books = books;
        }
    }

    @Context
    Providers providers;

    public long getSize(Collection<Book> books, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        return (-1);
    }

    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {

        return (Collection.class.isAssignableFrom(type));

    }

    public void writeTo(Collection<Book> books, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException {

        providers.getMessageBodyWriter(BooksWrapper.class, genericType, annotations, mediaType).
                writeTo(new BooksWrapper(books), type, genericType, annotations, mediaType, httpHeaders, entityStream);

    }

}
