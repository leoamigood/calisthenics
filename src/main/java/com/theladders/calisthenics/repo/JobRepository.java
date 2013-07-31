package com.theladders.calisthenics.repo;

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
    public boolean addJob(Recruiter recruiter, Job job);
    public Jobs find(Recruiter recruiter);
}
