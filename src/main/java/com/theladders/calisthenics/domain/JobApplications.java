package com.theladders.calisthenics.domain;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:17 PM
 */
public class JobApplications implements Iterable<JobApplication>
{
    private Set<JobApplication> jobApplications = new LinkedHashSet<>();

    public JobApplications(JobApplication ...applications)
    {
        jobApplications.addAll(Arrays.asList(applications));
    }

    public JobApplications(Set<JobApplication> applications)
    {
        jobApplications.addAll(applications);
    }

    public boolean add(JobApplication application)
    {
        return jobApplications.add(application);
    }

    public boolean addAll(JobApplications applications)
    {
        if (applications != null) {
            return jobApplications.addAll(applications.jobApplications);
        }
        return false;
    }

    public int size()
    {
        return jobApplications.size();
    }

    @Override
    public Iterator<JobApplication> iterator()
    {
        return jobApplications.iterator();
    }

    public boolean isEmpty()
    {
        return jobApplications.isEmpty();
    }

    public boolean contains(JobApplication application)
    {
        return jobApplications.contains(application);
    }
}
