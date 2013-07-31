package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:39 PM
 */
public class JobApplicationFilters implements JobApplicationMatcher
{
    private List<JobApplicationMatcher> filters = new ArrayList<>();

    public JobApplicationFilters(final JobApplicationMatcher... filters)
    {
        this.filters.addAll(Arrays.asList(filters));
    }

    @Override
    public boolean match(final JobApplication application)
    {
        if (application == null) throw new NullPointerException();

        for (JobApplicationMatcher filter : filters) {
            if (!filter.match(application)) return false;
        }
        return true;
    }

    public boolean matchAll(final JobApplications applications)
    {
        for (JobApplication application: applications) {
            if (!match(application)) return false;
        }

        return true;
    }

    public JobApplications apply(final JobApplications applications)
    {
        JobApplications applied = new JobApplications();
        for (JobApplicationMatcher filter: filters) {
            apply(applications, applied, filter);
        }
        return applied;
    }

    private void apply(final JobApplications applications,
                       final JobApplications applied,
                       final JobApplicationMatcher filter)
    {
        for (JobApplication application: applications) {
            if (filter.match(application)) {
                applied.add(application);
            }
        }
    }
}
