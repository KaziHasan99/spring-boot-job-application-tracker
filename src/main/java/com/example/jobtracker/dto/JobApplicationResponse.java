package com.example.jobtracker.dto;

public class JobApplicationResponse {

    private int id;
    private String company;
    private String position;

    public JobApplicationResponse(int id, String company, String position) {
        this.id = id;
        this.company = company;
        this.position = position;
    }

    public JobApplicationResponse() {

    }

    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }
}
