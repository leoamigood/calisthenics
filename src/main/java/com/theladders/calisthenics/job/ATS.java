package com.theladders.calisthenics.job;

import com.theladders.calisthenics.resume.Resume;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:56 PM
 */
public class ATS extends Job {

    @Override
    public boolean isCompliant(Resume resume)
    {
        return true;
    }
}
