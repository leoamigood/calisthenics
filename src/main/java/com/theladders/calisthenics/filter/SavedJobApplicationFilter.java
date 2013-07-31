package com.theladders.calisthenics.filter;

import com.theladders.calisthenics.job.application.JobApplication;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 1:53 PM
 */
public class SavedJobApplicationFilter implements JobMatcher
{
    @Override
    public boolean match(JobApplication application)
    {
        return application.isSaved();
    }
}
