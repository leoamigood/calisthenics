package com.theladders.calisthenics.job.application;

import com.theladders.calisthenics.actor.JobSeeker;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 2:07 PM
 */
public class SavedJobApplication extends JobApplication
{
    public SavedJobApplication(final JobSeeker seeker, final JobApplicationDetails info)
    {
        super(seeker, info);
    }

    @Override
    public JobApplicationState status()
    {
        return JobApplicationState.ON_WATCH_LIST;
    }
}
