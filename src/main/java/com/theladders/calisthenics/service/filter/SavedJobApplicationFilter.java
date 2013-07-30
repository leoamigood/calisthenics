package com.theladders.calisthenics.service.filter;

import com.theladders.calisthenics.domain.JobApplication;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 1:53 PM
 */
public class SavedJobApplicationFilter implements JobMatcher
{
    @Override
    public boolean match(JobApplication application)
    {
        return application.isSaved();
    }
}
