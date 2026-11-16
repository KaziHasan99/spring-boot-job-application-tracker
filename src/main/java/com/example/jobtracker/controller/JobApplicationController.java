package com.example.jobtracker.controller;

import com.example.jobtracker.dto.JobApplicationResponse;
import com.example.jobtracker.model.JobApplication;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.jobtracker.service.JobApplicationService;
import com.example.jobtracker.dto.JobApplicationRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;
import java.util.List;


@RestController
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {

        this.jobApplicationService = jobApplicationService;

    }

   @GetMapping("/applications")

    public List<JobApplicationResponse> getApplications() {

      List<JobApplication> applications = jobApplicationService.getAllApplications();

      List<JobApplicationResponse> responses = applications.stream()
              .map(application -> jobApplicationService.convertToResponse(application))
              .toList();

      return responses;
   }

   @GetMapping("/applications/{id}")

    public ResponseEntity<JobApplicationResponse> getApplicationsById(@PathVariable int id) {

       JobApplication job = jobApplicationService.getApplicationById(id);

           if (job != null) {
               JobApplicationResponse response =
                       jobApplicationService.convertToResponse(job);
               return ResponseEntity.ok(response);
           }

       return ResponseEntity.notFound().build();
   }

   @PostMapping("/applications")

    public ResponseEntity<String> addApplication(@RequestBody @Valid JobApplicationRequest job) {

       JobApplication application = new JobApplication();

       application.setCompany(job.getCompany());
       application.setPosition(job.getPosition());

       boolean added = jobApplicationService.addApplication(application);

           if (added) {
               return ResponseEntity.status(201).body("Application added for " + job.getCompany());

           }
       return ResponseEntity.status(409).body("Duplicate Application ID!");
   }

   @PutMapping("/applications/{id}")

    public ResponseEntity<String> updateApplication(@PathVariable int id,
                                                    @RequestBody JobApplication updatedJob) {

        boolean updated = jobApplicationService.updateApplication(id, updatedJob);

            if (updated) {
                return ResponseEntity.status(200).body("Application updated successfully");
            }

        return ResponseEntity.status(404).body("Application not found!");
   }

   @DeleteMapping("/applications/{id}")

    public ResponseEntity<String> deleteApplication(@PathVariable int id) {

       boolean removed = jobApplicationService.deleteApplication(id);

        if(removed) {
            return ResponseEntity.status(200).body("Application deleted successfully");
        }

        return ResponseEntity.status(404).body("Application not found!");
   }

   @GetMapping("/applications/company/{company}")
    public List<JobApplicationResponse> getApplicationsByCompany(@PathVariable String company) {
        List<JobApplication> applications = jobApplicationService.getApplicationsByCompany(company);

        List<JobApplicationResponse> responses = applications.stream()
                .map(application -> jobApplicationService.convertToResponse(application))
                .toList();

        return responses;
   }

   @GetMapping("/applications/position/{position}")
    public List<JobApplicationResponse> getApplicationsByPosition(@PathVariable String position) {

        List<JobApplication> applications = jobApplicationService.getApplicationsByPosition(position);

        List<JobApplicationResponse> responses = applications.stream()
                .map(application -> jobApplicationService.convertToResponse(application))
                .toList();

        return responses;
   }

   @GetMapping("/applications/paginated")
    public Page<JobApplicationResponse> getApplicationsPaginated(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String direction) {

        Page<JobApplication> applications = jobApplicationService.getApplicationsPaginated(page, size, sortBy, direction);

        Page<JobApplicationResponse> responses = applications.map(
                application -> jobApplicationService.convertToResponse(application)
        );
        return responses;
   }

}