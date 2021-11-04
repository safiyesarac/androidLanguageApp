
package com.example.tre;

import java.util.HashMap;
import java.util.Map;




public class Author {


    private String id;

    private String firstName;

    private String lastName;

    private String dob;

    private String dod;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getDob() {
        return dob;
    }


    public void setDob(String dob) {
        this.dob = dob;
    }


    public String getDod() {
        return dod;
    }


    public void setDod(String dod) {
        this.dod = dod;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
