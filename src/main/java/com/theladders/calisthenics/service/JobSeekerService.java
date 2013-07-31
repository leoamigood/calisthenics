package com.theladders.calisthenics.service;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.filter.JobFilters;
import com.theladders.calisthenics.filter.SavedJobApplicationFilter;
import com.theladders.calisthenics.job.Job;
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
        JobApplications applications = appRepository.findSaved(jobSeeker, job);
        if (applications.isEmpty()) {
            JobApplicationDetails details = new JobApplicationDetails(job, new Date());
            appRepository.save(new SavedJobApplication(jobSeeker, details));
        }
    }

    public JobApplications getSavedApplications(JobSeeker jobSeeker)
    {
        JobApplications applications = appRepository.find(jobSeeker);
        return new JobFilters(new SavedJobApplicationFilter()).apply(applications);
    }

    public JobApplication apply(JobSeeker jobSeeker,
                                Resume resume,
                                Job job,
                                JobPolicy policy)
    {
        Restrictions restrictions = policy.getRestrictions(jobSeeker, resume, job);
        if (restrictions.isNone()) {
            appRepository.save(new CompletedJobApplication(jobSeeker, resume, job));
        }
        return new DeniedJobApplication(jobSeeker, resume, job, restrictions);
    }

}
