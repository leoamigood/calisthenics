package com.theladders.calisthenics.service;

import com.theladders.calisthenics.JobFilters;
import com.theladders.calisthenics.domain.JobSeekers;
import com.theladders.calisthenics.domain.Jobs;
import com.theladders.calisthenics.domain.Recruiter;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RecruitingService {

    public boolean post(Recruiter recruiter, Jobs jobs);
    public Jobs list(Recruiter recruiter);
    public JobSeekers list(Recruiter recruiter, JobFilters filters);
}
