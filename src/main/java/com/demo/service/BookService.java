package com.demo.service;

import com.demo.model.Book;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Collection;

/**
 * Created by sanandasena on 2/18/2016.
 */
public interface BookService {

    ListenableFuture<Collection<Book>> getBooksAsync();

    ListenableFuture<Book> getBookAsync(final String id);

    ListenableFuture<Book> addBookAsync(final Book book);
}
