package com.theladders.calisthenics.report;

import java.io.IOException;
import java.io.Writer;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 4:23 PM
 */
public abstract class ReportFormatter
{
    protected Writer writer;

    public ReportFormatter(Writer writer)
    {
        this.writer = writer;
    }

    public abstract void write(final Iterable<Reportable> data) throws IOException;
}
