package com.theladders.calisthenics.job;

import com.theladders.calisthenics.domain.Job;
import com.theladders.calisthenics.domain.JobApplications;
import com.theladders.calisthenics.domain.JobSeeker;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/29/13
 * Time: 2:21 PM
 */
public interface JobApplicationRepository
{
    public JobApplications find(Job job);
    public JobApplications find(JobSeeker jobSeeker);
    public JobApplications findSaved(JobSeeker jobSeeker);

    public JobApplications saveJobApplication(JobSeeker seeker, Job job);
}
