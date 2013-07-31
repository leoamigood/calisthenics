package com.theladders.calisthenics.filter;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
import com.theladders.calisthenics.job.application.SavedJobApplication;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 1:54 PM
 */
public class SavedJobApplicationFilterTest
{
    SavedJobApplicationFilter filter;

    @Test
    public void testMatch() throws Exception
    {
        JobSeeker seeker = new JobSeeker();
        JobApplicationDetails info = new JobApplicationDetails(new ATS(), new Date());
        filter = new SavedJobApplicationFilter();

        JobApplication application = new JobApplication(seeker, info);
        assertFalse(filter.match(application));

        JobApplication saved = new SavedJobApplication(seeker, info);
        assertTrue(filter.match(saved));
    }
}
