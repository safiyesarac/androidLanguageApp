package com.example.tre;


public class Books
{
    private String num_sections;

    private String url_rss;

    private String totaltimesecs;

    private String copyright_year;

    private String url_text_source;

    private String description;

    private String language;

    private String title;

    private String url_project;

    private String totaltime;

    private String url_zip_file;

    private String url_librivox;

    private String id;

    private String url_other;

    private Authors[] authors;

    public String getNum_sections()
    {
        return num_sections;
    }

    public void setNum_sections(String num_sections)
    {
        this.num_sections = num_sections;
    }

    public String getUrl_rss()
    {
        return url_rss;
    }

    public void setUrl_rss(String url_rss)
    {
        this.url_rss = url_rss;
    }

    public String getTotaltimesecs()
    {
        return totaltimesecs;
    }

    public void setTotaltimesecs(String totaltimesecs)
    {
        this.totaltimesecs = totaltimesecs;
    }

    public String getCopyright_year()
    {
        return copyright_year;
    }

    public void setCopyright_year(String copyright_year)
    {
        this.copyright_year = copyright_year;
    }

    public String getUrl_text_source()
    {
        return url_text_source;
    }

    public void setUrl_text_source(String url_text_source)
    {
        this.url_text_source = url_text_source;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getUrl_project()
    {
        return url_project;
    }

    public void setUrl_project(String url_project)
    {
        this.url_project = url_project;
    }

    public String getTotaltime()
    {
        return totaltime;
    }

    public void setTotaltime(String totaltime)
    {
        this.totaltime = totaltime;
    }

    public String getUrl_zip_file()
    {
        return url_zip_file;
    }

    public void setUrl_zip_file(String url_zip_file)
    {
        this.url_zip_file = url_zip_file;
    }

    public String getUrl_librivox()
    {
        return url_librivox;
    }

    public void setUrl_librivox(String url_librivox)
    {
        this.url_librivox = url_librivox;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUrl_other()
    {
        return url_other;
    }

    public void setUrl_other(String url_other)
    {
        this.url_other = url_other;
    }

    public Authors[] getAuthors()
    {
        return authors;
    }

    public void setAuthors(Authors[] authors)
    {
        this.authors = authors;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [num_sections = "+num_sections+", url_rss = "+url_rss+", totaltimesecs = "+totaltimesecs+", copyright_year = "+copyright_year+", url_text_source = "+url_text_source+", description = "+description+", language = "+language+", title = "+title+", url_project = "+url_project+", totaltime = "+totaltime+", url_zip_file = "+url_zip_file+", url_librivox = "+url_librivox+", id = "+id+", url_other = "+url_other+", authors = "+authors+"]";
    }
}