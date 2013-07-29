package com.theladders.calisthenics.service.filter;

import com.theladders.calisthenics.domain.Job;
import com.theladders.calisthenics.domain.JobApplication;
import com.theladders.calisthenics.domain.JobApplications;
import com.theladders.calisthenics.domain.Jobs;
import org.apache.commons.collections.CollectionUtils;

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
    private List<JobMatcher> list = new ArrayList<>();

    public JobFilters(JobMatcher...filters)
    {
        this.list.addAll(Arrays.asList(filters));
    }

    @Override
    public boolean match(JobApplication application)
    {
        if (application == null) throw new NullPointerException();

        for (JobMatcher filter : list) {
            if (!filter.match(application)) return false;
        }
        return true;
    }

    public JobApplications apply(JobApplications applications)
    {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
