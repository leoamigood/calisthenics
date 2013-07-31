package com.theladders.calisthenics.job.application.filter;

import com.theladders.calisthenics.job.application.JobApplication;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 5:45 PM
 */
public class SameDayJobApplicationFilter implements JobApplicationMatcher
{
    private Date date;

    public SameDayJobApplicationFilter(final Date date)
    {
        this.date = date;
    }

    @Override
    public boolean match(final JobApplication application)
    {
        return DateUtils.isSameDay(application.date(), date);
    }

}
