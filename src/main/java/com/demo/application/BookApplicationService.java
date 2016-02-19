package com.demo.application;

import com.demo.service.BookService;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 * Created by sanandasena on 2/18/2016.
 */
public class BookApplicationService extends ResourceConfig {

    public BookApplicationService(final BookService bookService) {

        JacksonJsonProvider json = new JacksonJsonProvider().
                configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).
                configure(SerializationFeature.INDENT_OUTPUT, true);

        JacksonXMLProvider xml = new JacksonXMLProvider().
                configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).
                configure(SerializationFeature.INDENT_OUTPUT, true);

        packages("com.demo");
        register(new AbstractBinder() {
            protected void configure() {
                bind(bookService).to(BookService.class);
            }
        });

        register(json);
        register(xml);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }
}
