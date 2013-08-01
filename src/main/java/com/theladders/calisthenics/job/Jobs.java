package com.theladders.calisthenics.job;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

    public boolean add(final Job job)
    {
        return jobs.add(job);
    }

    public boolean contains(final Job job)
    {
        return jobs.contains(job);
    }
}
