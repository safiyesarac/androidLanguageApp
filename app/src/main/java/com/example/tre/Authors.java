package com.example.tre;


public class Authors
{
    private String dob;

    private String dod;

    private String last_name;

    private String id;

    private String first_name;

    public String getDob ()
    {
        return dob;
    }

    public void setDob (String dob)
    {
        this.dob = dob;
    }

    public String getDod ()
    {
        return dod;
    }

    public void setDod (String dod)
    {
        this.dod = dod;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dob = "+dob+", dod = "+dod+", last_name = "+last_name+", id = "+id+", first_name = "+first_name+"]";
    }
}
