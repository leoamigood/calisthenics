package com.theladders.calisthenics.service;

import com.theladders.calisthenics.actor.JobSeekers;
import com.theladders.calisthenics.actor.Recruiter;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.JobRepository;
import com.theladders.calisthenics.job.Jobs;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplicationRepository;
import com.theladders.calisthenics.job.application.JobApplications;
import com.theladders.calisthenics.job.application.filter.JobApplicationFilters;
import com.theladders.confident.Maybe;

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

  public RecruitService(final JobRepository repository,
                        final JobApplicationRepository appRepository)
  {
    this.jobRepository = repository;
    this.appRepository = appRepository;
  }

  public Jobs postJobs(final Recruiter recruiter,
                       final Jobs jobs)
  {
    for (Job job : jobs) {
      jobRepository.save(recruiter, job);
    }

    return jobs;
  }

  public Jobs jobsPostedBy(final Recruiter recruiter)
  {
    return jobRepository.find(recruiter);
  }

  public JobApplications jobsApplicationsBy(final Recruiter recruiter)
  {
    Jobs jobs = jobRepository.find(recruiter);

    JobApplications combined = new JobApplications();
    for (Job job : jobs) {
      JobApplications applications = appRepository.findByJob(job);
      combined.addAll(applications);
    }

    return combined;
  }

  public JobSeekers applicantsBy(final Recruiter recruiter)
  {
    return applicantsBy(recruiter, Maybe.<JobApplicationFilters>nothing());
  }

  public JobSeekers applicantsBy(final Recruiter recruiter,
                                 final Maybe<JobApplicationFilters> filters)
  {
    JobApplications applications = jobsApplicationsBy(recruiter);

    return filters.isSomething() ? jobSeekersBy(filters.get().apply(applications)) : jobSeekersBy(applications);
  }

  private JobSeekers jobSeekersBy(final JobApplications applications)
  {
    JobSeekers seekers = new JobSeekers();
    for (JobApplication application : applications) {
      seekers.add(application.applicant());
    }

    return seekers;
  }
}
