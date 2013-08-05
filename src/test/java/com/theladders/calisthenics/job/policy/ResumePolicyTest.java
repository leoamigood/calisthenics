package com.theladders.calisthenics.job.policy;

import com.theladders.calisthenics.CalisthenicsTest;
import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.resume.BasicResume;
import com.theladders.calisthenics.resume.Resume;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/31/13
 * Time: 11:07 AM
 */
public class ResumePolicyTest extends CalisthenicsTest
{
    ResumePolicy policy = new ResumePolicy();

    @Test
    public void testMayApplyForJob() throws Exception
    {
        Resume resume = new BasicResume();
        JobSeeker jobSeeker = new JobSeeker("John Smith", resume);

        Assert.assertNotNull(policy.restrictBy(jobSeeker, resume, ats));
    }
}
