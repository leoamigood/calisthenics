package com.theladders.calisthenics.service.filter;

import com.theladders.calisthenics.domain.ATS;
import com.theladders.calisthenics.domain.JobApplication;
import com.theladders.calisthenics.domain.JobApplicationDetails;
import com.theladders.calisthenics.domain.JobSeeker;
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
    SeekerJobFilter filter;

    @Test
    public void testMatch() throws Exception
    {
        JobSeeker seeker = new JobSeeker();
        JobApplicationDetails info = new JobApplicationDetails(new ATS(), new Date());
        filter = new SeekerJobFilter(seeker);

        JobApplication application = new JobApplication(seeker, info);
        assertTrue(filter.match(application));
        assertTrue(filter.match(new JobApplication(seeker, null)));

        assertFalse(filter.match(new JobApplication(new JobSeeker(), info)));
        assertFalse(filter.match(null));
    }

}
