package com.theladders.calisthenics.job.domain;

import com.theladders.calisthenics.CalisthenicsTest;
import com.theladders.calisthenics.resume.BasicResume;
import com.theladders.calisthenics.resume.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 1:16 PM
 */
public class JobSeekerTest extends CalisthenicsTest
{
    Resume resume;

    @Before
    public void setUp() {
        resume = new BasicResume();
    }

    @Test
    public void testIsOwner() throws Exception {
        jobSeeker.addResume(resume);

        assertTrue(jobSeeker.isOwner(resume));
        assertFalse(jobSeeker.isOwner(new BasicResume()));
        assertFalse(jobSeeker.isOwner(null));
    }

}
