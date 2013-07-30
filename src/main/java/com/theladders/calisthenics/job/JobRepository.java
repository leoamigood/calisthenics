package com.theladders.calisthenics.job;

import com.theladders.calisthenics.domain.Job;
import com.theladders.calisthenics.domain.Jobs;
import com.theladders.calisthenics.domain.Recruiter;

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
