package com.theladders.calisthenics.service.filter;

import com.theladders.calisthenics.domain.JobApplication;
import com.theladders.calisthenics.domain.JobApplications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:39 PM
 */
public class JobFilters implements JobMatcher
{
    private List<JobMatcher> filters = new ArrayList<>();

    public JobFilters(JobMatcher...filters)
    {
        this.filters.addAll(Arrays.asList(filters));
    }

    @Override
    public boolean match(JobApplication application)
    {
        if (application == null) throw new NullPointerException();

        for (JobMatcher filter : filters) {
            if (!filter.match(application)) return false;
        }
        return true;
    }

    public boolean matchAll(JobApplications applications)
    {
        for (JobApplication application: applications) {
            if (!match(application)) return false;
        }

        return true;
    }

    public JobApplications apply(JobApplications applications)
    {
        JobApplications applied = new JobApplications();
        for (JobMatcher filter: filters) {
            apply(applications, applied, filter);
        }
        return applied;
    }

    private void apply(JobApplications applications, JobApplications applied, JobMatcher filter)
    {
        for (JobApplication application: applications) {
            if (filter.match(application)) {
                applied.add(application);
            }
        }
    }
}
