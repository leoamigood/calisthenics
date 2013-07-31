package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.application.CompletedJobApplication;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.resume.BasicResume;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/31/13
 * Time: 3:54 PM
 */
public class AppliedJobApplicationFilterTest
{
    AppliedJobApplicationFilter filter;

    @Test
    public void testMatch() throws Exception
    {
        JobSeeker jobSeeker = new JobSeeker();
        filter = new AppliedJobApplicationFilter();

        JobApplication application = new CompletedJobApplication(jobSeeker, new BasicResume(), new ATS());
        assertTrue(filter.match(application));
    }
}