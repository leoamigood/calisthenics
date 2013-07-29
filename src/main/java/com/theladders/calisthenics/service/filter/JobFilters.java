package com.theladders.calisthenics.service.filter;

import com.theladders.calisthenics.domain.JobApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:39 PM
 */
public class JobFilters implements JobFilter
{
    private List<JobFilter> filters = new ArrayList<>();

    public JobFilters(final JobFilter filter)
    {
        this.filters = new ArrayList<JobFilter>() {{ add(filter); }};
    }

    public boolean add(JobFilter filter)
    {
        return filters.add(filter);
    }

    @Override
    public boolean match(JobApplication application)
    {
        for (JobFilter filter : filters) {
            if (filter.match(application)) return true;
        }
        return false;
    }
}
