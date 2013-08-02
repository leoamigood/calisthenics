package com.theladders.calisthenics.dao;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplications;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/29/13
 * Time: 2:21 PM
 */
public interface JobApplicationRepository
{
    public JobApplications findByJob(final Job job);
    public JobApplications findByJobSeeker(final JobSeeker jobSeeker);
    public JobApplications findByDate(final Date date);

    public void save(final JobApplication application);
}
