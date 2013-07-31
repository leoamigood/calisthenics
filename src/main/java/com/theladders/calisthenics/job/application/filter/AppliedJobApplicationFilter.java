package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.application.JobApplication;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/31/13
 * Time: 3:53 PM
 */
public class AppliedJobApplicationFilter implements JobApplicationMatcher
{
    @Override
    public boolean match(final JobApplication application)
    {
        return application.status().isAccepted();
    }
}
