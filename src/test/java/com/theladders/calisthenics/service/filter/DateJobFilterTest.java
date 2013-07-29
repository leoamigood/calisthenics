package com.theladders.calisthenics.service.filter;

import com.theladders.calisthenics.domain.ATS;
import com.theladders.calisthenics.domain.JobApplication;
import com.theladders.calisthenics.domain.JobApplicationDetails;
import com.theladders.calisthenics.domain.JobSeeker;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/17/13
 * Time: 9:57 AM
 */
public class DateJobFilterTest
{
    DateJobFilter filter;

    Date now = new Date();

    @Test
    public void testMatch() throws Exception
    {
        JobSeeker seeker = new JobSeeker();
        JobApplicationDetails info = new JobApplicationDetails(new ATS(), now);
        filter = new DateJobFilter(now);

        JobApplication application = new JobApplication(seeker, info);
        assertTrue(filter.match(application));
        assertTrue(filter.match(new JobApplication(new JobSeeker(), info)));
        assertTrue(filter.match(new JobApplication(null, info)));

        JobApplicationDetails another = new JobApplicationDetails(new ATS(), getYesterdayDate());
        assertFalse(filter.match(new JobApplication(seeker, another)));
        assertFalse(filter.match(null));
    }

    private Date getYesterdayDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

}

