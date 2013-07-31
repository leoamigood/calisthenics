package com.theladders.calisthenics.repo;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.application.filter.JobFilter;
import com.theladders.calisthenics.job.application.filter.JobApplicationFilters;
import com.theladders.calisthenics.job.application.filter.SavedJobApplicationFilter;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplications;
import org.apache.commons.collections.Factory;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 3:24 PM
 */
public class InMemoryJobApplicationRepository implements JobApplicationRepository
{
    private Map<JobSeeker, JobApplications> data = lazy(new HashMap<JobSeeker, JobApplications>());

    private Map<JobSeeker, JobApplications> lazy(HashMap<JobSeeker, JobApplications> map)
    {
        return LazyMap.decorate(map, new Factory()
        {
            @Override
            public Object create()
            {
                return new JobApplications();
            }
        });
    }

    @Override
    public JobApplications find(final Job job)
    {
        JobApplicationFilters filters = new JobApplicationFilters(new JobFilter(job));

        JobApplications found = new JobApplications();
        for (JobApplications applications: data.values()) {
            found.addAll(filters.apply(applications));
        }

        return found;
    }

    @Override
    public JobApplications find(final JobSeeker jobSeeker)
    {
        return data.get(jobSeeker);
    }

    @Override
    public void save(final JobApplication application)
    {
        data.get(application.applicant()).addAll(new JobApplications(application));
    }
}
