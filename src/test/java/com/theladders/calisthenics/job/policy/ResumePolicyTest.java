package com.theladders.calisthenics.job.policy;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.resume.BasicResume;
import com.theladders.calisthenics.resume.Resume;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/31/13
 * Time: 11:07 AM
 */
public class ResumePolicyTest
{
    ResumePolicy policy = new ResumePolicy();

    @Test
    public void testMayApplyForJob() throws Exception
    {
        Resume resume = new BasicResume();
        JobSeeker jobSeeker = new JobSeeker(resume);
        Job job = new ATS();

        Assert.assertNotNull(policy.getRestrictions(jobSeeker, resume, job));
    }
}