package com.theladders.calisthenics.service.filter;

import com.theladders.calisthenics.domain.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: lamigud
 * Date: 7/17/13
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class JobFiltersTest
{
    Date now = new Date();
    JobSeeker seeker = new JobSeeker();

    JobApplication application = new JobApplication(seeker, new JobApplicationDetails(new ATS(), now));

    @Test
    public void testMatchByJobSeeker() throws Exception
    {
        JobFilters filters = new JobFilters(new SeekerJobFilter(seeker));
        assertTrue(filters.match(application));
    }

    @Test
    public void testDoesNotMatchByJobSeeker() throws Exception
    {
        JobApplication different = new JobApplication(new JobSeeker(), new JobApplicationDetails(new ATS(), now));

        JobFilters filters = new JobFilters(new SeekerJobFilter(seeker));
        assertFalse(filters.match(different));
    }

    @Test
    public void testMatchByDate() throws Exception
    {
        JobFilters filters = new JobFilters(new SameDayJobFilter(now));
        assertTrue(filters.match(application));
    }

    @Test
    public void testDoesNotMatchByDate() throws Exception
    {
        JobFilters filters = new JobFilters(new SameDayJobFilter(getYesterdayDate()));
        assertFalse(filters.match(application));
    }

    @Test
    public void testMatchByJobSeekerAndDate() throws Exception
    {
        JobFilters filters = new JobFilters(new SeekerJobFilter(seeker), new SameDayJobFilter(now));
        assertTrue(filters.match(application));
    }

    @Test
    public void testDoesNotMatchByJobSeekerButNotDate() throws Exception
    {
        JobFilters filters = new JobFilters(new SeekerJobFilter(seeker), new SameDayJobFilter(getYesterdayDate()));
        assertFalse(filters.match(application));
    }

    @Test
    public void testDoesNotMatchByMultipleDates() throws Exception
    {
        JobFilters filters = new JobFilters(new SameDayJobFilter(getYesterdayDate()), new SameDayJobFilter(now));
        assertFalse(filters.match(application));

        JobFilters asArray = new JobFilters(new JobMatcher[] {new SameDayJobFilter(getYesterdayDate()), new SameDayJobFilter(now)});
        assertFalse(asArray.match(application));
    }

    @Test(expected = NullPointerException.class)
    public void testNullApplication() throws Exception
    {
        JobFilters filters = new JobFilters(new SeekerJobFilter(seeker));
        assertFalse(filters.match(null));
    }

    @Test
    @Ignore
    public void testApply()
    {
        JobFilters filters = new JobFilters(new SeekerJobFilter(seeker));
        assertNotNull(filters.apply(new JobApplications(application)));
    }

    private Date getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
