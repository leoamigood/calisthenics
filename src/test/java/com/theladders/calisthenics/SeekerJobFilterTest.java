package com.theladders.calisthenics;

import com.theladders.calisthenics.domain.ATS;
import com.theladders.calisthenics.domain.JobApplication;
import com.theladders.calisthenics.domain.JobSeeker;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:52 PM
 */
public class SeekerJobFilterTest {

    SeekerJobFilter filter;

    @Test
    public void testMatch() throws Exception {
        JobSeeker seeker = new JobSeeker();
        filter = new SeekerJobFilter(seeker);

        JobApplication application = new JobApplication(seeker, new ATS());
        assertTrue(filter.match(application));
        assertFalse(filter.match(new JobApplication(new JobSeeker(), new ATS())));
        assertFalse(filter.match(null));
    }

}
