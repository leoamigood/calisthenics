package com.theladders.calisthenics.filter;

import com.theladders.calisthenics.job.application.JobApplication;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 5:45 PM
 */
public class SameDayJobFilter implements JobMatcher
{
    private Date date;

    public SameDayJobFilter(Date date)
    {
        this.date = date;
    }

    @Override
    public boolean match(JobApplication application)
    {
        return DateUtils.isSameDay(application.getDate(), date);
    }

}
