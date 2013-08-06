package com.theladders.calisthenics.report;

import com.theladders.calisthenics.CalisthenicsTest;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
import com.theladders.calisthenics.job.application.JobApplications;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/6/13
 * Time: 9:20 AM
 */
public class ByJobAggregatorTest extends CalisthenicsTest
{
  ByJobAggregator aggregator;

  @Test
  public void testAggregateEmpty() throws Exception
  {
    JobApplications applications = new JobApplications();
    aggregator = new ByJobAggregator(applications);

    Map<Job, Integer> map = aggregator.apply();
    assertEquals(0, map.size());
  }

  @Before
  public void setUp()
  {
    JobApplication[] applications = new JobApplication[]{
        new JobApplication(jobSeeker, new JobApplicationDetails(jReq, new Date())),
        new JobApplication(jobSeeker, new JobApplicationDetails(ats, new Date())),
        new JobApplication(jobSeeker, new JobApplicationDetails(jReq, getYesterdayDate())),
        new JobApplication(jobSeeker, new JobApplicationDetails(jReq, getTwoDaysAgoDate()))
    };
    aggregator = new ByJobAggregator(new JobApplications(applications));
  }

  @Test
  public void testAggregate() throws Exception
  {
    Map<Job, Integer> map = aggregator.apply();
    assertEquals(1, map.get(ats).intValue());
    assertEquals(3, map.get(jReq).intValue());
    assertEquals(2, map.size());
  }

  @Test
  public void testReport() throws Exception
  {
    Iterable<Reportable> report = aggregator.report();
    assertNotNull(report.iterator());
  }
}
