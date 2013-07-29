package com.theladders.calisthenics.job;

import com.theladders.calisthenics.domain.Job;
import com.theladders.calisthenics.domain.JobApplications;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/29/13
 * Time: 2:21 PM
 */
public interface JobApplicationRepository
{
    public JobApplications findByJob(Job job);
}
