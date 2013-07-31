package com.theladders.calisthenics.job.application;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.policy.Restrictions;
import com.theladders.calisthenics.resume.Resume;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/31/13
 * Time: 11:00 AM
 */
public class DeniedJobApplication extends JobApplication
{
    public DeniedJobApplication(final JobSeeker jobSeeker,
                                final Resume resume,
                                final Job job,
                                final Restrictions restrictions)
    {
        super(jobSeeker, new JobApplicationDetails(job, new Date()));
    }

    @Override
    public JobApplicationState status()
    {
        return JobApplicationState.DENIED;
    }
}