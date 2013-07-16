package com.theladders.calisthenics.domain;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:25 PM
 */
public class JobApplication {
    private JobSeeker seeker;
    private Job job;

    public JobApplication(JobSeeker seeker, Job job) {
        this.seeker = seeker;
        this.job = job;
    }

    public boolean filedBy(JobSeeker applicant) {
        return this.seeker.equals(applicant);
    }
}
