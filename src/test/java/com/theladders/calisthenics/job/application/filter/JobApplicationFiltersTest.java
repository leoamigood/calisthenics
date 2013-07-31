package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
import com.theladders.calisthenics.job.application.JobApplications;
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
public class JobApplicationFiltersTest
{
    Date now = new Date();
    JobSeeker seeker = new JobSeeker();

    JobApplication application = new JobApplication(seeker, new JobApplicationDetails(new ATS(), now));

    @Test
    public void testMatchByJobSeeker() throws Exception
    {
        JobApplicationFilters filters = new JobApplicationFilters(new SeekerJobApplicationFilter(seeker));
        assertTrue(filters.match(application));
    }

    @Test
    public void testDoesNotMatchByJobSeeker() throws Exception
    {
        JobApplication different = new JobApplication(new JobSeeker(), new JobApplicationDetails(new ATS(), now));

        JobApplicationFilters filters = new JobApplicationFilters(new SeekerJobApplicationFilter(seeker));
        assertFalse(filters.match(different));
    }

    @Test
    public void testMatchByDate() throws Exception
    {
        JobApplicationFilters filters = new JobApplicationFilters(new SameDayJobApplicationFilter(now));
        assertTrue(filters.match(application));
    }

    @Test
    public void testDoesNotMatchByDate() throws Exception
    {
        JobApplicationFilters filters = new JobApplicationFilters(new SameDayJobApplicationFilter(getYesterdayDate()));
        assertFalse(filters.match(application));
    }

    @Test
    public void testMatchByJobSeekerAndDate() throws Exception
    {
        JobApplicationFilters filters = new JobApplicationFilters(new SeekerJobApplicationFilter(seeker), new SameDayJobApplicationFilter(now));
        assertTrue(filters.match(application));
    }

    @Test
    public void testDoesNotMatchByJobSeekerButNotDate() throws Exception
    {
        JobApplicationFilters filters = new JobApplicationFilters(new SeekerJobApplicationFilter(seeker), new SameDayJobApplicationFilter(getYesterdayDate()));
        assertFalse(filters.match(application));
    }

    @Test
    public void testDoesNotMatchByMultipleDates() throws Exception
    {
        JobApplicationFilters filters = new JobApplicationFilters(new SameDayJobApplicationFilter(getYesterdayDate()), new SameDayJobApplicationFilter(now));
        assertFalse(filters.match(application));

        JobApplicationFilters asArray = new JobApplicationFilters(new JobApplicationMatcher[] {new SameDayJobApplicationFilter(getYesterdayDate()), new SameDayJobApplicationFilter(now)});
        assertFalse(asArray.match(application));
    }

    @Test(expected = NullPointerException.class)
    public void testNullApplication() throws Exception
    {
        JobApplicationFilters filters = new JobApplicationFilters(new SeekerJobApplicationFilter(seeker));
        assertFalse(filters.match(null));
    }

    @Test
    public void testMatchAllByJobSeeker() throws Exception
    {
        JobApplication additional = new JobApplication(seeker, new JobApplicationDetails(new ATS(), now));

        JobApplicationFilters filters = new JobApplicationFilters(new SeekerJobApplicationFilter(seeker));
        JobApplications applications = new JobApplications(application, additional);
        assertTrue(filters.matchAll(applications));

        applications.add(new JobApplication(new JobSeeker(), new JobApplicationDetails(new ATS(), now)));
        assertFalse(filters.matchAll(applications));
    }

    @Test
    public void testApply()
    {
        JobApplicationFilters filters = new JobApplicationFilters(new SeekerJobApplicationFilter(seeker));
        JobApplications applied = filters.apply(new JobApplications(application));
        assertNotNull(applied);
        assertTrue(applied.contains(application));
    }

    private Date getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
