package com.theladders.calisthenics.dao;

import com.theladders.calisthenics.actor.Recruiter;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.Jobs;
import org.apache.commons.collections.Factory;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 2:18 PM
 */
public class InMemoryJobRepository implements JobRepository
{
    private Map<Recruiter, Jobs> data = lazy(new HashMap<Recruiter, Jobs>());

    private Map<Recruiter, Jobs> lazy(final HashMap<Recruiter, Jobs> map)
    {
        return LazyMap.decorate(map, new Factory()
        {
            @Override
            public Object create()
            {
                return new Jobs();
            }
        });
    }

    @Override
    public boolean save(Recruiter recruiter, Job job)
    {
        return data.get(recruiter).add(job);
    }

    @Override
    public Jobs find(Recruiter recruiter)
    {
        return data.get(recruiter);
    }
}
