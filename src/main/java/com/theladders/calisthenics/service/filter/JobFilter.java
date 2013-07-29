package com.theladders.calisthenics.service.filter;

import com.theladders.calisthenics.domain.Job;
import com.theladders.calisthenics.domain.JobApplication;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/29/13
 * Time: 1:52 PM
 */
public class JobFilter implements JobMatcher
{
    private Job job;

    public JobFilter(Job job) {
        this.job = job;
    }

    @Override
    public boolean match(JobApplication application)
    {
        return application.getJob().equals(job);
    }

}
