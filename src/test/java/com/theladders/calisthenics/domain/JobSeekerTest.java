package com.theladders.calisthenics.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lamigud
 * Date: 7/16/13
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class JobSeekerTest {

    Resume resume;
    JobSeeker seeker;

    @Before
    public void setUp() {
        resume = new Resume();
        seeker = new JobSeeker();
    }

    @Test
    public void testIsOwner() throws Exception {
        seeker.addResume(resume);

        assertTrue(seeker.isOwner(resume));
        assertFalse(seeker.isOwner(new Resume()));
        assertFalse(seeker.isOwner(null));
    }

}
