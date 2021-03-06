package com.theladders.calisthenics.job;

import com.theladders.calisthenics.actor.Recruiter;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/29/13
 * Time: 10:46 AM
 */
public interface JobRepository
{
  public boolean save(Recruiter recruiter, Job job);
  public Jobs find(Recruiter recruiter);
}
