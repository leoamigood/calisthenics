package com.theladders.calisthenics.job.application;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.Job;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/29/13
 * Time: 2:21 PM
 */
public interface JobApplicationRepository
{
  public JobApplications findByJob(Job job);
  public JobApplications findByJobSeeker(JobSeeker jobSeeker);
  public JobApplications findByDate(Date date);
  public void save(JobApplication application);
}
