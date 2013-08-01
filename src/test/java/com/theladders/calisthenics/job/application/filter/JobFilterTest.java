package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.CalisthenicsTest;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 11:52 AM
 */
public class JobFilterTest extends CalisthenicsTest
{
    Job job = new ATS();
    JobFilter filter = new JobFilter(job);

    @Test
    public void testMatchMultipleApplicationsBySameJob() throws Exception
    {
        JobApplication application1 = new JobApplication(jobSeeker, new JobApplicationDetails(job, new Date()));
        Assert.assertTrue(filter.match(application1));

        JobApplication application2 = new JobApplication(jobSeeker, new JobApplicationDetails(job, new Date()));
        Assert.assertTrue(filter.match(application2));
    }

    @Test
    public void testMatchDifferentDay() throws Exception
    {
        JobApplication anotherDate = new JobApplication(jobSeeker, new JobApplicationDetails(job, getYesterdayDate()));
        Assert.assertTrue(filter.match(anotherDate));
    }

    @Test
    public void testDoesNotMatchDifferentJob() throws Exception
    {
        JobApplication anotherATS = new JobApplication(jobSeeker, new JobApplicationDetails(new ATS(), new Date()));
        Assert.assertFalse(filter.match(anotherATS));
    }
}
