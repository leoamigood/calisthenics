package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.application.JobApplication;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:44 PM
 */
public class SeekerJobApplicationFilter implements JobApplicationMatcher
{
    private JobSeeker seeker;

    public SeekerJobApplicationFilter(final JobSeeker seeker)
    {
        this.seeker = seeker;
    }

    @Override
    public boolean match(final JobApplication application)
    {
        return application.applicant().equals(seeker);
    }

}
