package com.theladders.calisthenics.job;

import com.theladders.calisthenics.resume.Resume;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:56 PM
 */
public class ATS extends Job {

    public ATS(String title)
    {
        super(title);
    }

    @Override
    public boolean isCompliant(final Resume resume)
    {
        return true;
    }
}
