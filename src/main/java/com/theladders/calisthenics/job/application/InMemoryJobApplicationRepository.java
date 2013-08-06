package com.theladders.calisthenics.job.application;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.filter.JobApplicationFilters;
import com.theladders.calisthenics.job.application.filter.JobFilter;
import com.theladders.calisthenics.job.application.filter.SameDayJobApplicationFilter;
import org.apache.commons.collections.Factory;
import org.apache.commons.collections.map.LazyMap;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 3:24 PM
 */
public class InMemoryJobApplicationRepository implements JobApplicationRepository
{
  private Map<JobSeeker, JobApplications> data = lazy(new HashMap<JobSeeker, JobApplications>());

  private Map<JobSeeker, JobApplications> lazy(final HashMap<JobSeeker, JobApplications> map)
  {
    return LazyMap.decorate(map, new Factory()
    {
      @Override
      public Object create()
      {
        return new JobApplications();
      }
    });
  }

  @Override
  public JobApplications findByJob(Job job)
  {
    return filter(new JobApplicationFilters(new JobFilter(job)));
  }

  @Override
  public JobApplications findByJobSeeker(JobSeeker jobSeeker)
  {
    return data.get(jobSeeker);
  }

  @Override
  public JobApplications findByDate(Date date)
  {
    return filter(new JobApplicationFilters(new SameDayJobApplicationFilter(date)));
  }

  private JobApplications filter(JobApplicationFilters filters)
  {
    JobApplications found = new JobApplications();
    for (JobApplications applications : data.values()) {
      found.addAll(filters.apply(applications));
    }

    return found;
  }

  @Override
  public void save(JobApplication application)
  {
    data.get(application.applicant()).add(application);
  }
}
