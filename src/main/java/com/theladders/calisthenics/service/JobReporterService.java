package com.theladders.calisthenics.service;

import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplications;
import com.theladders.calisthenics.report.Aggregator;
import com.theladders.calisthenics.report.CSVJobReportFormatter;
import com.theladders.calisthenics.report.ReportFormatter;
import com.theladders.calisthenics.report.Reportable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

  public void reportJobSeekersBy(final ReportFormatter formatter,
                                 final Date date) throws IOException
  {
    JobApplications applications = jobSeekerService.jobsAppliedBy(date);
    formatter.write(format(applications));
  }

  public Iterable<Reportable> format(Iterable<JobApplication> applications)
  {
    List<Reportable> list = new ArrayList<>();
    for (JobApplication application: applications)
    {
      list.add(application);
    }
    return Collections.unmodifiableCollection(list);
  }

  public void reportJobApplicationsWith(final CSVJobReportFormatter formatter,
                                        final Aggregator aggregator) throws IOException
  {
    formatter.write(aggregator.report());
  }
}
