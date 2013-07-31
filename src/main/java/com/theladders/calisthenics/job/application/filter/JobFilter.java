package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplication;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/29/13
 * Time: 1:52 PM
 */
public class JobFilter implements JobApplicationMatcher
{
    private Job job;

    public JobFilter(final Job job)
    {
        this.job = job;
    }

    @Override
    public boolean match(final JobApplication application)
    {
        return application.job().equals(job);
    }

}
