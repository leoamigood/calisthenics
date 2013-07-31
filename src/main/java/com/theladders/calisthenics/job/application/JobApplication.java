package com.theladders.calisthenics.job.application;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.Job;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:25 PM
 */
public class JobApplication
{
    private JobSeeker jobSeeker;
    private JobApplicationDetails info;

    public JobApplication(final JobSeeker jobSeeker, final JobApplicationDetails info)
    {
        this.jobSeeker = jobSeeker;
        this.info = info;
    }

    public JobSeeker applicant()
    {
        return jobSeeker;
    }

    public Date date()
    {
        return info.date();
    }

    public Job job()
    {
        return info.job();
    }

    public JobApplicationState status()
    {
        return JobApplicationState.INCOMPLETE;
    }
}
