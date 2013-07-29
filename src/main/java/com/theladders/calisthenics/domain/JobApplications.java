package com.theladders.calisthenics.domain;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:17 PM
 */
public class JobApplications
{
    private Set<JobApplication> jobApplications = new LinkedHashSet<>();

    public JobApplications(JobApplication ...applications)
    {
        jobApplications.addAll(Arrays.asList(applications));
    }

    public boolean add(JobApplication application)
    {
        return jobApplications.add(application);
    }

    public boolean addAll(JobApplications applications)
    {
        return jobApplications.addAll(applications.jobApplications);
    }

    public int size()
    {
        return jobApplications.size();
    }
}
