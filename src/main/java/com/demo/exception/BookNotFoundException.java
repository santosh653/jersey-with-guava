package com.demo.exception;

/**
 * Created by sanandasena on 2/19/2016.
 */
public class BookNotFoundException extends Exception {

    private static final long serialVersionUID = 3864807149699066519L;

    public BookNotFoundException(String m) {
        super(m);
    }
}
