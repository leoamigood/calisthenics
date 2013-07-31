package com.theladders.calisthenics.job.policy;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.resume.Resume;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/31/13
 * Time: 5:09 PM
 */
public class NullPolicy implements JobPolicy
{
    @Override
    public Restrictions getRestrictions(JobSeeker jobSeeker, Resume resume, Job job)
    {
        return new Restrictions();
    }
}
