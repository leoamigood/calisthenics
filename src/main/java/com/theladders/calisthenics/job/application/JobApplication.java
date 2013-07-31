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

    public JobApplication(JobSeeker jobSeeker, JobApplicationDetails info)
    {
        this.jobSeeker = jobSeeker;
        this.info = info;
    }

    public JobSeeker getApplicant()
    {
        return jobSeeker;
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
