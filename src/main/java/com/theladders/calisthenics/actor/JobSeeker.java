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
    private Resumes resumes = new Resumes();

    public JobSeeker(){}

    public JobSeeker(final Resume resume)
    {
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
