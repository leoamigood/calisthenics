package com.theladders.calisthenics.repo;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplications;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/29/13
 * Time: 2:21 PM
 */
public interface JobApplicationRepository
{
    public JobApplications find(Job job);
    public JobApplications find(JobSeeker jobSeeker);

    public void save(JobApplication application);
}