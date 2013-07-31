package com.theladders.calisthenics.filter;

import com.theladders.calisthenics.job.application.JobApplication;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:39 PM
 */
public interface JobMatcher
{
    public boolean match(JobApplication application);
}
