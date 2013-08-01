package com.theladders.calisthenics.actor;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 12:02 PM
 */
public class RecruiterTest
{
    @Test
    public void testIdentity()
    {
        Recruiter recruiter1 = new Recruiter("John Smith");
        assertNotNull(recruiter1.id());
        assertEquals("John Smith", recruiter1.name());

        Recruiter recruiter2 = new Recruiter("John Smith");
        assertNotNull(recruiter2.id());
        assertEquals("John Smith", recruiter2.name());

        assertFalse(recruiter1.equals(recruiter2));
        assertFalse(recruiter1.id().equals(recruiter2.id()));
        assertTrue(recruiter1.name().equals(recruiter2.name()));
    }

}
