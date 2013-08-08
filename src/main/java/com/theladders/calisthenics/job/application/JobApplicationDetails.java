package com.theladders.calisthenics.job.application;

import com.theladders.calisthenics.job.Job;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 6:00 PM
 */
public class JobApplicationDetails
{
  private Job job;
  private Date appliedOn;

  public JobApplicationDetails(Job job, Date appliedOn)
  {
    this.job = job;
    this.appliedOn = appliedOn;
  }

  public Job job()
  {
    return job;
  }

  public Date date()
  {
    return appliedOn;
  }

}
