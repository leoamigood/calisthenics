package com.theladders.calisthenics.domain;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 6:00 PM
 */
public class JobApplicationInfo {

    private Job job;
    private Date appliedOn;

    public JobApplicationInfo(Job job, Date appliedOn) {
        this.job = job;
        this.appliedOn = appliedOn;
    }

    public boolean isDated(Date date) {
        return appliedOn.equals(date);
    }

}
