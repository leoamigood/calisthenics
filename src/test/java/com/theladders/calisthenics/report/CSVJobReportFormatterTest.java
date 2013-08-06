package com.theladders.calisthenics.report;

import com.theladders.calisthenics.CalisthenicsTest;
import com.theladders.calisthenics.job.application.CompletedJobApplication;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.resume.BasicResume;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    final JobApplication atsApp = new CompletedJobApplication(jobSeeker, new BasicResume(), ats);
    final JobApplication jReqApp = new CompletedJobApplication(jobSeeker, new BasicResume(), jReq);
    List<Reportable> applications = new ArrayList<Reportable>() {{ add(atsApp); add(jReqApp); }};
    reporter.write(applications);

    assertEquals(2 * 3, StringUtils.countMatches(writer.toString(), ","));
    assertEquals(2, StringUtils.countMatches(writer.toString(), "\n"));
  }
}
