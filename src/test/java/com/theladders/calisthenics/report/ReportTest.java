package com.theladders.calisthenics.report;

import com.theladders.calisthenics.CalisthenicsTest;
import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.actor.Recruiter;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
import com.theladders.calisthenics.job.application.JobApplications;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/8/13
 * Time: 10:12 AM
 */
public abstract class ReportTest extends CalisthenicsTest
{
  protected JobApplications applications;
  protected JobApplicationAggregator aggregator;

  Recruiter andyRecruiter = new Recruiter("Andy Recruiter");
  JobSeeker chrisJobSeeker = new JobSeeker("Christian Seeker");
  Job atsJobFromChris = new ATS("Another ATS job by Chris", andyRecruiter);

  public ReportTest()
  {

    applications = new JobApplications(new JobApplication[]{
        new JobApplication(jobSeeker, new JobApplicationDetails(jReq, new Date())),
        new JobApplication(jobSeeker, new JobApplicationDetails(ats, new Date())),
        new JobApplication(jobSeeker, new JobApplicationDetails(jReq, getYesterdayDate())),
        new JobApplication(jobSeeker, new JobApplicationDetails(jReq, getTwoDaysAgoDate())),
        new JobApplication(chrisJobSeeker, new JobApplicationDetails(atsJobFromChris, new Date())),
        new JobApplication(chrisJobSeeker, new JobApplicationDetails(atsJobFromChris, getTwoDaysAgoDate()))
    });
  }

  @Test
  public void testAggregateEmpty() throws Exception
  {
    JobApplications applications = new JobApplications();
    aggregator = new ByJobApplicationAggregator(applications);

    Map<Job, Integer> map = aggregator.apply();
    assertEquals(0, map.size());
  }

  @Test
  public void testReport() throws Exception
  {
    Iterable<Reportable> report = aggregator.report();
    assertNotNull(report.iterator());
  }
}
