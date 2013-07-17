package com.theladders.calisthenics.service.filter;

import com.theladders.calisthenics.domain.JobApplication;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 5:45 PM
 */
public class DateJobFilter implements JobFilter {

    private Date date;

    public DateJobFilter(Date date) {
        this.date = date;
    }

    @Override
    public boolean match(JobApplication application) {
        return application != null &&
                application.isAppliedOn(date);
    }
}
