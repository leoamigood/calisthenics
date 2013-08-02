package com.theladders.calisthenics.report;

import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplications;

import java.io.IOException;
import java.io.Writer;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 4:24 PM
 */
public class CSVJobReportFormatter extends ReportFormatter
{
    private static final String FIELD_SEPARATOR = ",";

    public CSVJobReportFormatter(Writer writer)
    {
        super(writer);
    }

    @Override
    public void write(JobApplications applications) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        for (JobApplication application: applications)
        {
            sb.append(application.date().toString()).append(FIELD_SEPARATOR).
                    append(application.applicant().toString()).append(FIELD_SEPARATOR).
                    append(application.job().toString()).
                    append(System.lineSeparator());

            writer.write(sb.toString());
        }
    }
}
