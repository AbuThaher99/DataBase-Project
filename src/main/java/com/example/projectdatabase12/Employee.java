package com.example.projectdatabase12;


public abstract class Employee {

    private String GID;

    public Employee(String GID) {
        this.GID = GID;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String gID) {
        GID = gID;
    }

}
