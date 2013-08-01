package com.theladders.calisthenics.actor;

import com.theladders.calisthenics.resume.Resume;
import com.theladders.calisthenics.resume.Resumes;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:23 PM
 */
public class JobSeeker
{
    private String name;
    private Resumes resumes = new Resumes();

    public JobSeeker(final String name)
    {
        this.name = name;
    }

    public JobSeeker(final String name, final Resume resume)
    {
        this(name);
        addResume(resume);
    }

    public boolean addResume(final Resume resume)
    {
        return resumes.add(resume);
    }

    public boolean isOwner(final Resume resume)
    {
        return resumes.contains(resume);
    }


}
