package com.theladders.calisthenics.domain;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 6:00 PM
 */
public class JobApplicationDetails
{
    private Job job;
    private Date appliedOn;

    public JobApplicationDetails(Job job, Date appliedOn)
    {
        this.job = job;
        this.appliedOn = appliedOn;
    }

    public Job getJob() {
        return job;
    }

    public Date getDate()
    {
        return appliedOn;
    }

}
