package com.example.jobtracker.dto;
import jakarta.validation.constraints.NotBlank;

public class JobApplicationRequest {

    @NotBlank(message = "Company name is required")
    private String company;
    @NotBlank(message = "Position is required")
    private String position;

    public JobApplicationRequest() {

    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}


