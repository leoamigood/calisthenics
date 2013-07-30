package com.theladders.calisthenics.job;

import com.theladders.calisthenics.domain.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 3:40 PM
 */
public class InMemoryJobApplicationRepositoryTest
{
    JobApplicationRepository repository = new InMemoryJobApplicationRepository();

    @Test
    public void testFindByJob() throws Exception
    {
        Job ats = new ATS();
        Job jReq = new JReq();
        JobSeeker jobSeeker = new JobSeeker();
        repository.saveJobApplication(jobSeeker, ats);
        repository.saveJobApplication(jobSeeker, jReq);

        JobApplications atsApplications = repository.find(ats);
        assertEquals(1, atsApplications.size());

        JobApplications jReqApplications = repository.find(jReq);
        assertEquals(1, jReqApplications.size());
    }

    @Test
    public void testFindByJobSeeker() throws Exception
    {
        JobSeeker jobSeeker = new JobSeeker();
        repository.saveJobApplication(jobSeeker, new ATS());
        repository.saveJobApplication(jobSeeker, new JReq());
        JobApplications applications = repository.find(jobSeeker);
        assertEquals(2, applications.size());
    }

    @Test
    public void testFindSaved() throws Exception
    {

    }

    @Test
    public void testSaveJobApplication() throws Exception
    {

    }
}
