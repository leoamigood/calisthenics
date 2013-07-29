package com.theladders.calisthenics.service.filter;

import com.theladders.calisthenics.domain.JobApplication;
import com.theladders.calisthenics.domain.JobSeeker;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:44 PM
 */
public class SeekerJobFilter implements JobMatcher
{
    private JobSeeker seeker;

    public SeekerJobFilter(JobSeeker seeker)
    {
        this.seeker = seeker;
    }

    @Override
    public boolean match(JobApplication application)
    {
        return application.getApplicant().equals(seeker);
    }

}
