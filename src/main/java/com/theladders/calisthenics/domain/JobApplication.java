package com.theladders.calisthenics.domain;

import java.util.Date;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:25 PM
 */
public class JobApplication {

    private JobSeeker seeker;
    private JobApplicationInfo info;

    public JobApplication(JobSeeker seeker, JobApplicationInfo info) {
        this.seeker = seeker;
        this.info = info;
    }

    public boolean isFiledBy(JobSeeker applicant) {
        return this.seeker.equals(applicant);
    }

    public boolean isAppliedOn(Date date) {
        return info.isDated(date);
    }
}
