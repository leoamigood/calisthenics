package com.theladders.calisthenics;

import com.theladders.calisthenics.domain.JobApplication;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 2:39 PM
 */
public interface JobFilter {

    public boolean match(JobApplication application);

}
