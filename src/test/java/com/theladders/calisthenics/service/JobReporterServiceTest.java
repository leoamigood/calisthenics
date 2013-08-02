package com.theladders.calisthenics.service;

import com.theladders.calisthenics.report.CSVJobReportFormatter;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 4:04 PM
 */
public class JobReporterServiceTest
{
    JobReporterService service;

    @Test
    @Ignore
    public void testJobReportByDate()
    {
        CSVJobReportFormatter formatter = mock(CSVJobReportFormatter.class);
//        service.report(formatter);
//
//        verify(formatter)
    }
    
}
