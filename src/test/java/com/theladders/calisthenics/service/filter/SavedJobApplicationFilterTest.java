package com.theladders.calisthenics.service.filter;

import com.theladders.calisthenics.domain.ATS;
import com.theladders.calisthenics.domain.JobApplication;
import com.theladders.calisthenics.domain.JobApplicationDetails;
import com.theladders.calisthenics.domain.JobSeeker;
import com.theladders.calisthenics.job.SavedJobApplication;
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
