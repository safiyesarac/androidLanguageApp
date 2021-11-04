
package com.example.tre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class AllBooksPojo {


    private List<Book> books = null;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public List<Book> getBooks() {
        return books;
    }


    public void setBooks(List<Book> books) {
        this.books = books;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
