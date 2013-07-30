package com.theladders.calisthenics.job;

import com.theladders.calisthenics.domain.JobApplication;
import com.theladders.calisthenics.domain.JobApplicationDetails;
import com.theladders.calisthenics.domain.JobSeeker;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 2:07 PM
 */
public class SavedJobApplication extends JobApplication
{
    public SavedJobApplication(JobSeeker seeker, JobApplicationDetails info)
    {
        super(seeker, info);
    }

    @Override
    public boolean isSaved()
    {
        return true;
    }
}
