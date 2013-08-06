package com.theladders.calisthenics.job.application;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.report.Reportable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:25 PM
 */
public class JobApplication implements Reportable
{
  private JobSeeker jobSeeker;
  private JobApplicationDetails info;

  public JobApplication(JobSeeker jobSeeker,
                        JobApplicationDetails info)
  {
    this.jobSeeker = jobSeeker;
    this.info = info;
  }

  public JobSeeker applicant()
  {
    return jobSeeker;
  }

  public Date date()
  {
    return info.date();
  }

  public Job job()
  {
    return info.job();
  }

  public JobApplicationState status()
  {
    return JobApplicationState.INCOMPLETE;
  }

  @Override
  public Iterator<String> chunks()
  {
    return new ArrayList<String>() {
      {
        add(date().toString());
        add(jobSeeker.toString());
        add(job().toString());
        add(job().recruiter().toString());
      }
    }.iterator();
  }
}

