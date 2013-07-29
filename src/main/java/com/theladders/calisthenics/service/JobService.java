package com.theladders.calisthenics.service;

import com.theladders.calisthenics.domain.*;
import com.theladders.calisthenics.job.JobApplicationRepository;
import com.theladders.calisthenics.job.JobRepository;
import com.theladders.calisthenics.service.filter.JobFilters;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class JobService
{
    private JobRepository jobsRepository;
    private JobApplicationRepository applicationRepository;

    public JobService(JobRepository repository)
    {
        this.jobsRepository = repository;
    }

    public Jobs postJobs(Recruiter recruiter, Jobs jobs)
    {
        for (Job job : jobs) {
            jobsRepository.addJob(recruiter, job);
        }

        return jobs;
    }

    public Jobs getJobs(Recruiter recruiter) {
        return jobsRepository.findByRecruiter(recruiter);
    }

    public JobApplications getJobApplications(Recruiter recruiter)
    {
        Jobs jobs = jobsRepository.findByRecruiter(recruiter);

        JobApplications combined = new JobApplications();
        for (Job job: jobs) {
            JobApplications applications = applicationRepository.findByJob(job);
            combined.addAll(applications);
        }

        return combined;
    }

    public JobApplications getJobApplicationsByRecruiter(Recruiter recruiter, JobFilters filters)
    {
        JobApplications applications = getJobApplications(recruiter);
        return filters.apply(applications);
    }

}
