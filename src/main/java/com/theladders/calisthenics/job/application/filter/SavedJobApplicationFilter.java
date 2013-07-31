package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.job.application.JobApplication;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 1:53 PM
 */
public class SavedJobApplicationFilter implements JobApplicationMatcher
{
    @Override
    public boolean match(final JobApplication application)
    {
        return application.status().isSaved();
    }
}
