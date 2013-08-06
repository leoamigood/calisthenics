package com.theladders.calisthenics;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.actor.Recruiter;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.JReq;
import com.theladders.calisthenics.job.Job;

import java.util.Calendar;
import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 1:23 PM
 */
public class CalisthenicsTest
{
  public JobSeeker jobSeeker = new JobSeeker("John Seeker");
  public Recruiter recruiter = new Recruiter("Paul Recruiter");

  public Job ats = new ATS("ATS job title", recruiter);
  public Job jReq = new JReq("JReq job title", recruiter);

  public Date getYesterdayDate()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -1);
    return calendar.getTime();
  }

  public Date getTwoDaysAgoDate()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -2);
    return calendar.getTime();
  }

  public Date getEarlierToday()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MILLISECOND, -1);
    return calendar.getTime();
  }
}
