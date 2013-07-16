package com.theladders.calisthenics.service;

import com.theladders.calisthenics.domain.Job;
import com.theladders.calisthenics.domain.JobApplication;
import com.theladders.calisthenics.domain.JobApplications;
import com.theladders.calisthenics.domain.JobSeeker;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 12:50 PM
 */
public interface JobSeekerService {

    public JobApplications list(JobSeeker seeker);
    public void save(JobSeeker seeker, Job job);
    public JobApplication apply(JobSeeker seeker, Job job) throws Exception;

}
