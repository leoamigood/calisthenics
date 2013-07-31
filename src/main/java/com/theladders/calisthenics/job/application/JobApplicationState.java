package com.theladders.calisthenics.job.application;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/31/13
 * Time: 3:13 PM
 */
public enum  JobApplicationState
{
    SUCCESS,
    INCOMPLETE,
    ON_WATCH_LIST,
    DENIED;

    public boolean isAccepted() {
        return this == SUCCESS;
    }

    public boolean isSaved()
    {
        return this == ON_WATCH_LIST;
    }
}
