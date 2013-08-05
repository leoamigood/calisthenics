package com.theladders.calisthenics.job;

import com.theladders.calisthenics.CalisthenicsTest;
import com.theladders.calisthenics.actor.Recruiter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 2:21 PM
 */
public class InMemoryJobRepositoryTest extends CalisthenicsTest
{
    InMemoryJobRepository repository;

    @Before
    public void setUp()
    {
        repository = new InMemoryJobRepository();
    }

    @Test
    public void testSaveAndFindJob() throws Exception
    {
        repository.save(recruiter, ats);
        repository.save(new Recruiter("Barbara Recruiter"), ats);
        repository.save(recruiter, jReq);

        assertEquals(2, repository.find(recruiter).size());
    }

}
