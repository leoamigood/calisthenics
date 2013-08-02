package com.theladders.calisthenics.dao;

import com.theladders.calisthenics.actor.Recruiter;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.Jobs;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/29/13
 * Time: 10:46 AM
 */
public interface JobRepository
{
    public boolean save(final Recruiter recruiter, final Job job);
    public Jobs find(final Recruiter recruiter);
}
