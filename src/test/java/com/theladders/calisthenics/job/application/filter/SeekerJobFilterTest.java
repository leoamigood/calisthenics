package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:52 PM
 */
public class SeekerJobFilterTest
{
    SeekerJobApplicationFilter filter;

    @Test
    public void testMatch() throws Exception
    {
        JobSeeker seeker = new JobSeeker();
        JobApplicationDetails info = new JobApplicationDetails(new ATS(), new Date());
        filter = new SeekerJobApplicationFilter(seeker);

        JobApplication application = new JobApplication(seeker, info);
        assertTrue(filter.match(application));
        assertTrue(filter.match(new JobApplication(seeker, new JobApplicationDetails(new ATS(), new Date()))));

        assertFalse(filter.match(new JobApplication(new JobSeeker(), info)));
    }

}
