package com.theladders.calisthenics.job;

import com.theladders.calisthenics.resume.Resume;
import com.theladders.calisthenics.util.Identifier;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:23 PM
 */
public abstract class Job
{
    private Id id;
    private String title;

    public static class Id implements Identifier
    {
        private Integer id;

        public Id(Integer id)
        {
            this.id = id;
        }
    }

    public Id id ()
    {
        return id;
    }

    public String title()
    {
        return title;
    }

    public abstract boolean isCompliant(final Resume resume);
}
