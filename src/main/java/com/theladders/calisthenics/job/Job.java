package com.theladders.calisthenics.job;

import com.theladders.calisthenics.resume.Resume;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:23 PM
 */
public abstract class Job {
    public abstract boolean isCompliant(Resume resume);
}
