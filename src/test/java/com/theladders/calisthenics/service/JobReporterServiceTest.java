package com.theladders.calisthenics.service;

import com.theladders.calisthenics.CalisthenicsTest;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
import com.theladders.calisthenics.job.application.JobApplications;
import com.theladders.calisthenics.report.ByJobApplicationAggregator;
import com.theladders.calisthenics.report.CSVJobReportFormatter;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.mockito.internal.matchers.Matches;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 4:04 PM
 */
public class JobReporterServiceTest extends CalisthenicsTest
{
  JobSeekerService jobSeekerService = mock(JobSeekerService.class);
  JobReporterService jobReporterService = new JobReporterService(jobSeekerService);

  @Test
  public void testJobReportByDate() throws IOException
  {
    CSVJobReportFormatter formatter = mock(CSVJobReportFormatter.class);

    Date date = new Date();
    when(jobSeekerService.jobsAppliedBy(date)).thenReturn(new JobApplications());
    jobReporterService.reportJobSeekersBy(formatter, date);

    verify(jobSeekerService, times(1)).jobsAppliedBy(date);
    verify(formatter, times(1)).write(any(Iterable.class));
  }

  @Test
  public void testAggregatedReport() throws IOException
  {
    Writer writer = new StringWriter();
    CSVJobReportFormatter formatter = new CSVJobReportFormatter(writer);

    JobApplications applications = new JobApplications(new JobApplication(jobSeeker, new JobApplicationDetails(ats, new Date())));
    jobReporterService.reportJobApplicationsWith(formatter, new ByJobApplicationAggregator(applications));

    //"Fri Aug 02 09:51:56 EDT 2013,285506194 ATS job title,3",
    Matcher jobMatches = StringContains.containsString("ATS job title");
    Matcher totalMatches = StringContains.containsString(",1");
    Matches regexp = new Matches("\\d+\\s.*,\\d+\\n");
    assertThat(writer.toString(), AllOf.allOf(jobMatches, totalMatches, regexp));
  }

}
