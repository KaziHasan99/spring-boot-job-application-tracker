package com.example.jobtracker.service;

import com.example.jobtracker.dto.JobApplicationResponse;
import com.example.jobtracker.model.JobApplication;
import com.example.jobtracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {

        this.jobApplicationRepository = jobApplicationRepository;

    }

    public List<JobApplication> getAllApplications() {

        return jobApplicationRepository.findAll();
    }

    public JobApplication getApplicationById(int id) {

        return jobApplicationRepository.findById(id).orElse(null);
    }

    public boolean addApplication(JobApplication job) {

        jobApplicationRepository.save(job);
        return true;
    }

    public boolean updateApplication(int id, JobApplication updatedJob) {

        JobApplication existingJob = jobApplicationRepository.findById(id).orElse(null);

            if (existingJob == null) {
                return false;
            }

            existingJob.setCompany(updatedJob.getCompany());
            existingJob.setPosition(updatedJob.getPosition());
            jobApplicationRepository.save(existingJob);

        return true;
    }

    public boolean deleteApplication(int id) {

        boolean existingJob = jobApplicationRepository.existsById(id);

        if (!existingJob) {
            return false;
        }

        jobApplicationRepository.deleteById(id);
        return true;
    }

    public static JobApplicationResponse
    convertToResponse(JobApplication application) {

        JobApplicationResponse response = new JobApplicationResponse(
                application.getId(),
                application.getCompany(),
                application.getPosition()
        );

        return response;
    }

    public List<JobApplication> getApplicationsByCompany(String company) {

        return jobApplicationRepository.findByCompanyIgnoreCase(company);
    }

    public List<JobApplication> getApplicationsByPosition(String position) {

        return jobApplicationRepository.findByPositionIgnoreCase(position);
    }

    public Page<JobApplication> getApplicationsPaginated(int page, int size, String sortBy, String direction) {

        Sort sort;

        if(direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        }
        else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        return jobApplicationRepository.findAll(pageable);
    }

}
