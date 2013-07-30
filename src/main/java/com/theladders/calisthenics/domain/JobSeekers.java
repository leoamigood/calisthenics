package com.theladders.calisthenics.domain;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:33 PM
 */
public class JobSeekers
{
    private Set<JobSeeker> seekers = new LinkedHashSet<>();

    public boolean add(JobSeeker seeker)
    {
        return seekers.add(seeker);
    }

    public int size()
    {
        return seekers.size();
    }

    public boolean contains(JobSeeker seeker)
    {
        return seekers.contains(seeker);
    }
}
