package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.job.application.JobApplication;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:39 PM
 */
public interface JobApplicationMatcher
{
    public boolean match(JobApplication application);
}
