package com.theladders.calisthenics.service;

import com.theladders.calisthenics.actor.JobSeekers;
import com.theladders.calisthenics.actor.Recruiter;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.Jobs;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplications;
import com.theladders.calisthenics.job.application.filter.JobApplicationFilters;
import com.theladders.calisthenics.dao.JobApplicationRepository;
import com.theladders.calisthenics.dao.JobRepository;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecruitService
{
    private JobRepository jobRepository;
    private JobApplicationRepository appRepository;

    public RecruitService(final JobRepository repository, final JobApplicationRepository appRepository)
    {
        this.jobRepository = repository;
        this.appRepository = appRepository;
    }

    public Jobs postJobs(final Recruiter recruiter, final Jobs jobs)
    {
        for (Job job : jobs) {
            jobRepository.save(recruiter, job);
        }

        return jobs;
    }

    public Jobs getPostedJobs(final Recruiter recruiter) {
        return jobRepository.find(recruiter);
    }

    public JobApplications getJobApplications(final Recruiter recruiter)
    {
        Jobs jobs = jobRepository.find(recruiter);

        JobApplications combined = new JobApplications();
        for (Job job: jobs) {
            JobApplications applications = appRepository.findByJob(job);
            combined.addAll(applications);
        }

        return combined;
    }

    public JobSeekers getApplicants(final Recruiter recruiter) {
        return getApplicants(recruiter, null);
    }

    public JobSeekers getApplicants(final Recruiter recruiter, final JobApplicationFilters filters)
    {
        JobApplications applications = getJobApplications(recruiter);

        if (filters == null) {
            return getJobSeekers(applications);
        }

        return getJobSeekers(filters.apply(applications));
    }

    private JobSeekers getJobSeekers(final JobApplications applications)
    {
        JobSeekers seekers = new JobSeekers();
        for (JobApplication application: applications) {
            seekers.add(application.applicant());
        }

        return seekers;
    }


}
