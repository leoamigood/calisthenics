package com.theladders.calisthenics.service;

import com.theladders.calisthenics.domain.Job;
import com.theladders.calisthenics.domain.Jobs;
import com.theladders.calisthenics.domain.Recruiter;
import com.theladders.calisthenics.job.JobRepository;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecruitingService
{
    private JobRepository repository;

    public RecruitingService(JobRepository repository)
    {
        this.repository = repository;
    }

    public Jobs post(Recruiter recruiter, Jobs jobs)
    {
        for (Job job : jobs) {
            repository.addJob(recruiter, job);
        }

        return jobs;
    }

    public Jobs list(Recruiter recruiter)
    {
        return repository.findByRecruiter(recruiter);
    }

//    public JobSeekers list(Recruiter recruiter, JobFilters filters);

}
