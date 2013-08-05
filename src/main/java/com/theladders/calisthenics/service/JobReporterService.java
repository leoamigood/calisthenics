package com.theladders.calisthenics.service;

import com.theladders.calisthenics.report.ReportFormatter;

import java.io.IOException;
import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 4:04 PM
 */
public class JobReporterService
{
  private JobSeekerService jobSeekerService;

  public JobReporterService(final JobSeekerService service)
  {
    this.jobSeekerService = service;
  }

  public void report(final ReportFormatter formatter,
                     final Date date) throws IOException
  {
    formatter.write(jobSeekerService.jobsAppliedBy(date));
  }
}
