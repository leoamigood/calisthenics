package com.theladders.calisthenics.domain;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

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
}
