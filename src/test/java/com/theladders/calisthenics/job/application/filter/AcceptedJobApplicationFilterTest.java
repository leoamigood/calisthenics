package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.CalisthenicsTest;
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
public class AcceptedJobApplicationFilterTest extends CalisthenicsTest
{
    AcceptedJobApplicationFilter filter;

    @Test
    public void testMatch() throws Exception
    {
        filter = new AcceptedJobApplicationFilter();

        JobApplication application = new CompletedJobApplication(jobSeeker, new BasicResume(), ats);
        assertTrue(filter.match(application));
    }
}
