package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.CalisthenicsTest;
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
public class SeekerJobFilterTest extends CalisthenicsTest
{
    SeekerJobApplicationFilter filter;

    @Test
    public void testMatch() throws Exception
    {
        JobApplicationDetails info = new JobApplicationDetails(new ATS(), new Date());
        filter = new SeekerJobApplicationFilter(jobSeeker);

        JobApplication application = new JobApplication(jobSeeker, info);
        assertTrue(filter.match(application));
        assertTrue(filter.match(new JobApplication(jobSeeker, new JobApplicationDetails(new ATS(), new Date()))));

        assertFalse(filter.match(new JobApplication(new JobSeeker("Mary Smith"), info)));
    }

}
