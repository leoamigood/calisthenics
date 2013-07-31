package com.theladders.calisthenics.job.domain;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
import com.theladders.calisthenics.job.application.JobApplications;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/29/13
 * Time: 2:30 PM
 */
public class JobApplicationsTest
{
    @Test
    public void testAdd() throws Exception
    {
        JobApplications applications = new JobApplications();
        JobApplicationDetails details = new JobApplicationDetails(new ATS(), new Date());
        applications.add(new JobApplication(new JobSeeker(), details));
        assertEquals(1, applications.size());
    }

    @Test
    public void testAddMultiple() throws Exception
    {
        JobApplicationDetails details = new JobApplicationDetails(new ATS(), new Date());
        JobApplications applications = new JobApplications(new JobApplication(new JobSeeker(), details));
        applications.addAll(new JobApplications(new JobApplication(new JobSeeker(), details)));
        assertEquals(2, applications.size());
    }

    @Test
    public void testAddMultipleNull() throws Exception
    {
        JobApplicationDetails details = new JobApplicationDetails(new ATS(), new Date());
        JobApplications applications = new JobApplications(new JobApplication(new JobSeeker(), details));
        applications.addAll(new JobApplications(new JobApplication(new JobSeeker(), details)));
        applications.addAll(null);
        assertEquals(2, applications.size());
    }

    @Test
    public void testIsEmpty() throws Exception
    {
        JobApplicationDetails details = new JobApplicationDetails(new ATS(), new Date());
        JobApplications applications = new JobApplications();
        assertTrue(applications.isEmpty());

        applications.add(new JobApplication(new JobSeeker(), details));
        assertFalse(applications.isEmpty());

        applications.addAll(new JobApplications(new JobApplication(new JobSeeker(), details)));
        assertFalse(applications.isEmpty());
    }

    @Test
    public void testJobs()
    {
        JobApplications applications = new JobApplications();
        assertEquals(0, applications.jobs().size());

        JobApplicationDetails details = new JobApplicationDetails(new ATS(), new Date());
        applications.add(new JobApplication(new JobSeeker(), details));
        assertEquals(1, applications.jobs().size());
    }
}
