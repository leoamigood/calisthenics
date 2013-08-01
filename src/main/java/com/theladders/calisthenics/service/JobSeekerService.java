package com.theladders.calisthenics.service;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.Jobs;
import com.theladders.calisthenics.job.application.*;
import com.theladders.calisthenics.job.application.filter.AppliedJobApplicationFilter;
import com.theladders.calisthenics.job.application.filter.JobApplicationFilters;
import com.theladders.calisthenics.job.application.filter.JobFilter;
import com.theladders.calisthenics.job.application.filter.SavedJobApplicationFilter;
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

    public JobSeekerService(final JobApplicationRepository appRepository)
    {
        this.appRepository = appRepository;
    }

    public void saveJobApplication(final JobSeeker jobSeeker, final Job job)
    {
        JobApplicationFilters filters = new JobApplicationFilters(new JobFilter(job));

        JobApplications applications = filters.apply(appRepository.findByJobSeeker(jobSeeker));
        if (applications.isEmpty()) {
            JobApplicationDetails details = new JobApplicationDetails(job, new Date());
            appRepository.save(new SavedJobApplication(jobSeeker, details));
        }
    }

    public Jobs getJobsSaved(final JobSeeker jobSeeker)
    {
        JobApplicationFilters filters = new JobApplicationFilters(new SavedJobApplicationFilter());
        JobApplications applications = filters.apply(appRepository.findByJobSeeker(jobSeeker));
        return applications.jobs();
    }

    public JobApplication apply(final JobSeeker jobSeeker,
                                final Resume resume,
                                final Job job,
                                final JobPolicy policy)
    {
        Restrictions restrictions = policy.getRestrictions(jobSeeker, resume, job);
        if (restrictions.isNone()) {
            JobApplication application = new CompletedJobApplication(jobSeeker, resume, job);
            appRepository.save(application);
            return application;
        }
        return new DeniedJobApplication(jobSeeker, resume, job, restrictions);
    }

    public Jobs getJobsApplied(final JobSeeker jobSeeker)
    {
        JobApplicationFilters filters = new JobApplicationFilters(new AppliedJobApplicationFilter());
        return filters.apply(appRepository.findByJobSeeker(jobSeeker)).jobs();
    }

    public JobApplications getJobApplications(Date date)
    {
        return appRepository.findByDate(date);
    }

}
