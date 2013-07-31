package com.theladders.calisthenics.job.domain;

import com.theladders.calisthenics.actor.JobSeeker;
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
public class JobSeekerTest {

    Resume resume;
    JobSeeker seeker;

    @Before
    public void setUp() {
        resume = new BasicResume();
        seeker = new JobSeeker();
    }

    @Test
    public void testIsOwner() throws Exception {
        seeker.addResume(resume);

        assertTrue(seeker.isOwner(resume));
        assertFalse(seeker.isOwner(new BasicResume()));
        assertFalse(seeker.isOwner(null));
    }

}
