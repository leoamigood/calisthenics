package com.theladders.calisthenics.repo;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.filter.JobFilter;
import com.theladders.calisthenics.filter.JobFilters;
import com.theladders.calisthenics.filter.SavedJobApplicationFilter;
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
        JobFilters filters = new JobFilters(new JobFilter(job));

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
    public JobApplications findSaved(JobSeeker jobSeeker)
    {
        JobFilters filters = new JobFilters(new SavedJobApplicationFilter());
        return filters.apply(find(jobSeeker));
    }

    @Override
    public JobApplications findSaved(final JobSeeker jobSeeker, Job job)
    {
        JobFilters filters = new JobFilters(new JobFilter(job));
        return filters.apply(findSaved(jobSeeker));
    }

    @Override
    public void save(final JobApplication application)
    {
        data.get(application.getApplicant()).addAll(new JobApplications(application));
    }
}
