package com.demo.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by sanandasena on 2/18/2016.
 */
@JsonPropertyOrder({"id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1898800404830858049L;
    @NotNull(message = "title is a required field")
    private String title;
    @NotNull(message = "author is a required field")
    private String author;
    private Date published;
    private String id;

    private HashMap<String, Object> extras = new HashMap<String, Object>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonAnyGetter
    public HashMap<String, Object> getExtras() {
        return extras;
    }

    @JsonAnySetter
    public void set(String key, Object value) {
        this.extras.put(key, value);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", published=" + published +
                ", id='" + id + '\'' +
                ", extras=" + extras +
                '}';
    }
}
