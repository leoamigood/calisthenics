package com.theladders.calisthenics.job.application;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.resume.Resume;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/31/13
 * Time: 10:58 AM
 */
public class CompletedJobApplication extends JobApplication
{
    public CompletedJobApplication(JobSeeker jobSeeker,
                                   Resume resume,
                                   Job job)
    {
        super(jobSeeker, new JobApplicationDetails(job, new Date()));
    }

    @Override
    public JobApplicationState status()
    {
        return JobApplicationState.SUCCESS;
    }
}
