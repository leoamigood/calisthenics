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
 * Created with IntelliJ IDEA.
 * User: lamigud
 * Date: 7/17/13
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class JobFiltersTest
{
    JobSeeker seeker = new JobSeeker();

    Date now = new Date();

    @Test
    public void testMatchByJobSeeker() throws Exception
    {
        JobApplication application = new JobApplication(seeker, new JobApplicationDetails(new ATS(), now));

        JobFilters filters = new JobFilters(new SeekerJobFilter(seeker));
        assertTrue(filters.match(application));
    }

    @Test
    public void testDoesNotMatchByJobSeeker() throws Exception
    {
        JobApplication application = new JobApplication(new JobSeeker(), new JobApplicationDetails(new ATS(), now));

        JobFilters filters = new JobFilters(new SeekerJobFilter(seeker));
        assertFalse(filters.match(application));
    }

    @Test
    public void testMatchByDate() throws Exception
    {
        JobApplication application = new JobApplication(seeker, new JobApplicationDetails(new ATS(), now));

        JobFilters filters = new JobFilters(new DateJobFilter(now));
        assertTrue(filters.match(application));
    }

    @Test
    public void testDoesNotMatchByDate() throws Exception
    {
        JobApplication application = new JobApplication(seeker, new JobApplicationDetails(new ATS(), now));

        JobFilters filters = new JobFilters(new DateJobFilter(getYesterdayDate()));
        assertFalse(filters.match(application));
    }

    @Test
    public void testMatchByJobSeekerAndDate() throws Exception
    {
        JobApplication application = new JobApplication(seeker, new JobApplicationDetails(new ATS(), now));

        JobFilters filters = new JobFilters(new SeekerJobFilter(seeker));
        filters.add(new DateJobFilter(now));
        assertFalse(filters.match(null));
    }

    @Test
    public void testDoesNotMatchByJobSeekerButNotDate() throws Exception
    {
        JobApplication application = new JobApplication(seeker, new JobApplicationDetails(new ATS(), now));

        JobFilters filters = new JobFilters(new SeekerJobFilter(seeker));
        filters.add(new DateJobFilter(getYesterdayDate()));
        assertFalse(filters.match(null));
    }

    @Test
    public void testMatchByMultipleDate() throws Exception
    {
        JobApplication application = new JobApplication(seeker, new JobApplicationDetails(new ATS(), now));

        JobFilters filters = new JobFilters(new DateJobFilter(getYesterdayDate()));
        filters.add(new DateJobFilter(now));
        assertTrue(filters.match(application));
    }

    @Test
    public void testNullApplication() throws Exception
    {
        JobFilters filters = new JobFilters(new SeekerJobFilter(seeker));
        assertFalse(filters.match(null));
    }

    private Date getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
