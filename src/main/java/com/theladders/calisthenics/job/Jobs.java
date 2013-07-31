package com.theladders.calisthenics.job;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:23 PM
 */
public class Jobs implements Iterable<Job>
{

    private List<Job> list = new ArrayList<>();

    public Jobs(Job ...jobs)
    {
        for (Job job: jobs) {
            list.add(job);
        }
    }

    @Override
    public Iterator<Job> iterator()
    {
        return list.iterator();
    }

    public int size()
    {
        return list.size();
    }
}
