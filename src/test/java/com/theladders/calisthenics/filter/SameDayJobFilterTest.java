package com.theladders.calisthenics.filter;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
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
public class SameDayJobFilterTest
{
    SameDayJobFilter filter;

    @Test
    public void testMatch() throws Exception
    {
        JobSeeker seeker = new JobSeeker();
        JobApplicationDetails info = new JobApplicationDetails(new ATS(), getEarlierToday());
        filter = new SameDayJobFilter(new Date());

        JobApplication application = new JobApplication(seeker, info);
        assertTrue(filter.match(application));
        assertTrue(filter.match(new JobApplication(new JobSeeker(), info)));
        assertTrue(filter.match(new JobApplication(null, info)));

        JobApplicationDetails another = new JobApplicationDetails(new ATS(), getYesterdayDate());
        assertFalse(filter.match(new JobApplication(seeker, another)));
    }

    private Date getYesterdayDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    private Date getEarlierToday()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }
}

