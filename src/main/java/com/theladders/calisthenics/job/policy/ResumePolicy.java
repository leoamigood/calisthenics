package com.theladders.calisthenics.job.policy;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.resume.Resume;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/31/13
 * Time: 11:03 AM
 */
public class ResumePolicy implements JobPolicy
{
    @Override
    public Restrictions getRestrictions(JobSeeker jobSeeker, Resume resume, Job job)
    {
        Restrictions restrictions = new Restrictions();
        if(!resume.isCompleted()) {
            restrictions.add(new IncompleteResumeRestriction(resume));
        }

        if (!jobSeeker.isOwner(resume)) {
            restrictions.add(new IncorrectResumeRestriction(resume));
        }

        if (!job.isCompliant(resume)) {
            restrictions.add(new IncompatibleResumeRestriction(job, resume));
        }

        return restrictions;
    }
}