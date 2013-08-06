package com.theladders.calisthenics.report;

import com.theladders.calisthenics.CalisthenicsTest;
import com.theladders.calisthenics.job.application.CompletedJobApplication;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.resume.BasicResume;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Matches;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 5:07 PM
 */
public class CSVJobReportFormatterTest extends CalisthenicsTest
{
  Writer writer;
  CSVJobReportFormatter reporter;

  @Before
  public void setUp()
  {
    writer = new StringWriter();
    reporter = new CSVJobReportFormatter(writer);
  }

  @Test
  public void testReport() throws Exception
  {
    final JobApplication application = new CompletedJobApplication(jobSeeker, new BasicResume(), ats);
    List<Reportable> applications = new ArrayList<Reportable>() {{ add(application); }};
    reporter.write(applications);

    //"Fri Aug 02 09:51:56 EDT 2013,1775388622 John Seeker,285506194 ATS job title",
    Matcher jobMatches = StringContains.containsString("ATS job title");
    Matcher jobSeekerMatches = StringContains.containsString("John Seeker");
    Matches regexp = new Matches(".*,\\d+.*,\\d+.*\\n");
    assertThat(writer.toString(), AllOf.allOf(jobSeekerMatches, jobMatches, regexp));
  }
}
