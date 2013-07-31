package com.theladders.calisthenics.service;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.application.filter.*;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.Jobs;
import com.theladders.calisthenics.job.application.*;
import com.theladders.calisthenics.job.policy.JobPolicy;
import com.theladders.calisthenics.job.policy.Restrictions;
import com.theladders.calisthenics.repo.JobApplicationRepository;
import com.theladders.calisthenics.resume.Resume;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 12:50 PM
 */
public class JobSeekerService
{
    private JobApplicationRepository appRepository;

    public JobSeekerService(JobApplicationRepository appRepository)
    {
        this.appRepository = appRepository;
    }

    public void saveJobApplication(JobSeeker jobSeeker, Job job)
    {
        JobApplicationFilters filters = new JobApplicationFilters(new JobFilter(job));

        JobApplications applications = filters.apply(appRepository.find(jobSeeker));
        if (applications.isEmpty()) {
            JobApplicationDetails details = new JobApplicationDetails(job, new Date());
            appRepository.save(new SavedJobApplication(jobSeeker, details));
        }
    }

    public JobApplications getJobsSaved(JobSeeker jobSeeker)
    {
        JobApplicationFilters filters = new JobApplicationFilters(new SavedJobApplicationFilter());
        return filters.apply(appRepository.find(jobSeeker));
    }

    public JobApplication apply(JobSeeker jobSeeker,
                                Resume resume,
                                Job job,
                                JobPolicy policy)
    {
        Restrictions restrictions = policy.getRestrictions(jobSeeker, resume, job);
        if (restrictions.isNone()) {
            JobApplication application = new CompletedJobApplication(jobSeeker, resume, job);
            appRepository.save(application);
            return application;
        }
        return new DeniedJobApplication(jobSeeker, resume, job, restrictions);
    }

    public Jobs getJobsApplied(JobSeeker jobSeeker)
    {
        JobApplicationFilters filters = new JobApplicationFilters(new AppliedJobApplicationFilter());
        return filters.apply(appRepository.find(jobSeeker)).jobs();
    }

}
