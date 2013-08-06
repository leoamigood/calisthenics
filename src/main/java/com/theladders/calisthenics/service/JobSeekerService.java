package com.theladders.calisthenics.service;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.Jobs;
import com.theladders.calisthenics.job.application.*;
import com.theladders.calisthenics.job.application.filter.AppliedJobApplicationFilter;
import com.theladders.calisthenics.job.application.filter.JobApplicationFilters;
import com.theladders.calisthenics.job.application.filter.JobFilter;
import com.theladders.calisthenics.job.application.filter.SavedJobApplicationFilter;
import com.theladders.calisthenics.job.policy.JobApplicationPolicy;
import com.theladders.calisthenics.job.policy.Restrictions;
import com.theladders.calisthenics.resume.Resume;
import com.theladders.confident.Maybe;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 12:50 PM
 */
public class JobSeekerService
{
  private JobApplicationRepository appRepository;
  private Maybe<JobApplicationPolicy> policy;

  public JobSeekerService(JobApplicationRepository appRepository,
                          Maybe<JobApplicationPolicy> policy)
  {
    this.appRepository = appRepository;
    this.policy = policy;
  }

  public void saveJobApplication(JobSeeker jobSeeker,
                                 Job job)
  {
    JobApplicationFilters filters = new JobApplicationFilters(new JobFilter(job));

    JobApplications applications = filters.apply(appRepository.findByJobSeeker(jobSeeker));
    if (applications.isEmpty()) {
      JobApplicationDetails details = new JobApplicationDetails(job, new Date());
      appRepository.save(new SavedJobApplication(jobSeeker, details));
    }
  }

  public Jobs jobsSaved(final JobSeeker jobSeeker)
  {
    JobApplicationFilters filters = new JobApplicationFilters(new SavedJobApplicationFilter());
    JobApplications applications = filters.apply(appRepository.findByJobSeeker(jobSeeker));
    return applications.jobs();
  }

  public JobApplication apply(JobSeeker jobSeeker,
                              Resume resume,
                              Job job)
  {
    Maybe<Restrictions> restrictions = policy.isSomething() ? policy.get().restrictBy(jobSeeker, resume, job) : Maybe.<Restrictions>nothing();

    if (restrictions.isNothing()) {
      JobApplication application = new CompletedJobApplication(jobSeeker, resume, job);
      appRepository.save(application);
      return application;
    }
    return new DeniedJobApplication(jobSeeker, resume, job, restrictions.get());
  }

  public Jobs jobsAppliedBy(JobSeeker jobSeeker)
  {
    JobApplicationFilters filters = new JobApplicationFilters(new AppliedJobApplicationFilter());
    return filters.apply(appRepository.findByJobSeeker(jobSeeker)).jobs();
  }

  public JobApplications jobsAppliedBy(Date date)
  {
    return appRepository.findByDate(date);
  }

}
