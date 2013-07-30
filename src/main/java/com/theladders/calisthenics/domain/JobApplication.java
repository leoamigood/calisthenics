package com.theladders.calisthenics.domain;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:25 PM
 */
public class JobApplication
{
    private JobSeeker seeker;
    private JobApplicationDetails info;

    public JobApplication(JobSeeker seeker, JobApplicationDetails info)
    {
        this.seeker = seeker;
        this.info = info;
    }

    public JobSeeker getApplicant()
    {
        return seeker;
    }

    public boolean isSaved()
    {
        return false;
    }

    public Date getDate()
    {
        return info.getDate();
    }

    public Job getJob()
    {
        return info.getJob();
    }
}
