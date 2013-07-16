package com.theladders.calisthenics.service;

import com.theladders.calisthenics.domain.*;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 12:50 PM
 */
public interface JobSeekerService {

    public void save(Job job, JobSeeker seeker);
    public JobApplication apply(ATS job, JobSeeker seeker);
    public JobApplication apply(JReq job, JobSeeker seeker, Resume resume);

}
