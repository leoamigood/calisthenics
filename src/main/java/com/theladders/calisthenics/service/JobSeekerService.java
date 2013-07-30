package com.theladders.calisthenics.service;

import com.theladders.calisthenics.domain.Job;
import com.theladders.calisthenics.domain.JobApplications;
import com.theladders.calisthenics.domain.JobSeeker;
import com.theladders.calisthenics.job.JobApplicationRepository;
import com.theladders.calisthenics.job.JobRepository;
import com.theladders.calisthenics.service.filter.JobFilters;
import com.theladders.calisthenics.service.filter.SavedJobApplicationFilter;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 12:50 PM
 */
public class JobSeekerService
{
    private JobRepository jobRepository;
    private JobApplicationRepository appRepository;

    public JobSeekerService(JobRepository jobRepository, JobApplicationRepository appRepository)
    {
        this.jobRepository = jobRepository;
        this.appRepository = appRepository;
    }

    public JobApplications saveJobApplication(JobSeeker seeker, Job job)
    {
        JobApplications applications = appRepository.find(job, seeker);
        if (applications.isEmpty()) {
            return appRepository.saveJobApplication(seeker, job);
        }

        return applications;
    }

    public JobApplications getSavedApplications(JobSeeker jobSeeker) {
        JobApplications applications = appRepository.find(jobSeeker);
        return new JobFilters(new SavedJobApplicationFilter()).apply(applications);
    }

//    public JobApplication apply(JobSeeker seeker, Job job) throws Exception;

}
