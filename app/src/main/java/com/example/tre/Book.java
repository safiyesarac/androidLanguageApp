
package com.example.tre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Book {


    private String id;

    private String title;

    private String description;

    private String urlTextSource;

    private String language;

    private String copyrightYear;

    private String numSections;

    private String urlRss;

    private String urlZipFile;

    private String urlProject;

    private String urlLibrivox;

    private String urlOther;

    private String totaltime;

    private Integer totaltimesecs;

    private List<Author> authors = null;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getUrlTextSource() {
        return urlTextSource;
    }


    public void setUrlTextSource(String urlTextSource) {
        this.urlTextSource = urlTextSource;
    }


    public String getLanguage() {
        return language;
    }


    public void setLanguage(String language) {
        this.language = language;
    }


    public String getCopyrightYear() {
        return copyrightYear;
    }


    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }


    public String getNumSections() {
        return numSections;
    }


    public void setNumSections(String numSections) {
        this.numSections = numSections;
    }


    public String getUrlRss() {
        return urlRss;
    }


    public void setUrlRss(String urlRss) {
        this.urlRss = urlRss;
    }


    public String getUrlZipFile() {
        return urlZipFile;
    }


    public void setUrlZipFile(String urlZipFile) {
        this.urlZipFile = urlZipFile;
    }


    public String getUrlProject() {
        return urlProject;
    }


    public void setUrlProject(String urlProject) {
        this.urlProject = urlProject;
    }


    public String getUrlLibrivox() {
        return urlLibrivox;
    }


    public void setUrlLibrivox(String urlLibrivox) {
        this.urlLibrivox = urlLibrivox;
    }


    public String getUrlOther() {
        return urlOther;
    }


    public void setUrlOther(String urlOther) {
        this.urlOther = urlOther;
    }


    public String getTotaltime() {
        return totaltime;
    }


    public void setTotaltime(String totaltime) {
        this.totaltime = totaltime;
    }


    public Integer getTotaltimesecs() {
        return totaltimesecs;
    }


    public void setTotaltimesecs(Integer totaltimesecs) {
        this.totaltimesecs = totaltimesecs;
    }


    public List<Author> getAuthors() {
        return authors;
    }


    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
