package com.theladders.calisthenics.job;

import java.util.*;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:23 PM
 */
public class Jobs implements Iterable<Job>
{
    private Set<Job> jobs = new HashSet<>();

    public Jobs(final Job ...jobs)
    {
        for (Job job: jobs) {
            this.jobs.add(job);
        }
    }

    @Override
    public Iterator<Job> iterator()
    {
        return jobs.iterator();
    }

    public int size()
    {
        return jobs.size();
    }

    public void add(Job job)
    {
        jobs.add(job);
    }

    public boolean contains(Job job)
    {
        return jobs.contains(job);
    }
}
