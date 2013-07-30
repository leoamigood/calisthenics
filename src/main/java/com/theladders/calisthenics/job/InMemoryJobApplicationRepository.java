package com.theladders.calisthenics.job;

import com.theladders.calisthenics.domain.Job;
import com.theladders.calisthenics.domain.JobApplicationDetails;
import com.theladders.calisthenics.domain.JobApplications;
import com.theladders.calisthenics.domain.JobSeeker;
import com.theladders.calisthenics.service.filter.JobFilter;
import com.theladders.calisthenics.service.filter.JobFilters;
import org.apache.commons.collections.Factory;
import org.apache.commons.collections.map.LazyMap;

import java.util.Date;
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
    public JobApplications findSaved(final JobSeeker jobSeeker)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public JobApplications saveJobApplication(final JobSeeker seeker, Job job)
    {
        JobApplicationDetails details = new JobApplicationDetails(job, new Date());
        JobApplications saved = new JobApplications(new SavedJobApplication(seeker, details));

        saved.addAll(data.put(seeker, saved));
        return saved;
    }
}
