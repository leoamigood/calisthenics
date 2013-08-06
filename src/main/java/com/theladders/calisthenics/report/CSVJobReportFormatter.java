package com.theladders.calisthenics.report;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

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
  public void write(Iterable<Reportable> report) throws IOException
  {
    for (Reportable reportable: report) {
      writeLine(reportable);
    }
  }

  private void writeLine(Reportable data) throws IOException
  {
    Iterator<String> iterator = data.chunks();
    while (iterator.hasNext())
    {
      writer.write(iterator.next());
      if (iterator.hasNext()) {
        writer.write(FIELD_SEPARATOR);
      }
    }
    writer.write(System.lineSeparator());
  }
}


