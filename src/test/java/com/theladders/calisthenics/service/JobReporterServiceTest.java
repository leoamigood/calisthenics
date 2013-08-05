package com.theladders.calisthenics.service;

import com.theladders.calisthenics.job.application.JobApplications;
import com.theladders.calisthenics.report.CSVJobReportFormatter;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 4:04 PM
 */
public class JobReporterServiceTest
{
  JobSeekerService jobSeekerService = mock(JobSeekerService.class);
  JobReporterService jobReporterService = new JobReporterService(jobSeekerService);

  @Test
  public void testJobReportByDate() throws IOException
  {
    CSVJobReportFormatter formatter = mock(CSVJobReportFormatter.class);

    Date date = new Date();
    jobReporterService.report(formatter, date);

    verify(jobSeekerService, times(1)).jobsAppliedBy(date);
    verify(formatter, times(1)).write(any(JobApplications.class));
  }

}
