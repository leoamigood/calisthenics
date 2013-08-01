package com.theladders.calisthenics.repo;

import com.theladders.calisthenics.CalisthenicsTest;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.JReq;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
import com.theladders.calisthenics.job.application.JobApplications;
import com.theladders.calisthenics.job.application.SavedJobApplication;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 3:40 PM
 */
public class InMemoryJobApplicationRepositoryTest extends CalisthenicsTest
{
    JobApplicationRepository repository;

    @Before
    public void setUp()
    {
        repository = new InMemoryJobApplicationRepository();
    }

    @Test
    public void testEmptyRepository()
    {
        assertNotNull(repository.findByJob(new ATS()));
        assertEquals(0, repository.findByJob(new ATS()).size());
        assertEquals(0, repository.findByJobSeeker(jobSeeker).size());
    }

    @Test
    public void testFindByJob() throws Exception
    {
        Job ats = new ATS();
        Job jReq = new JReq();
        repository.save(new SavedJobApplication(jobSeeker, new JobApplicationDetails(ats, new Date())));
        repository.save(new SavedJobApplication(jobSeeker, new JobApplicationDetails(jReq, new Date())));

        JobApplications atsApplications = repository.findByJob(ats);
        assertEquals(1, atsApplications.size());

        JobApplications jReqApplications = repository.findByJob(jReq);
        assertEquals(1, jReqApplications.size());
    }

    @Test
    public void testFindByJobSeeker() throws Exception
    {
        repository.save(new SavedJobApplication(jobSeeker, new JobApplicationDetails(new ATS(), new Date())));
        repository.save(new SavedJobApplication(jobSeeker, new JobApplicationDetails(new JReq(), new Date())));
        JobApplications applications = repository.findByJobSeeker(jobSeeker);
        assertEquals(2, applications.size());
    }

    @Test
    public void testFindSaved() throws Exception
    {
        repository.save(new SavedJobApplication(jobSeeker, new JobApplicationDetails(new ATS(), new Date())));
        repository.save(new SavedJobApplication(jobSeeker, new JobApplicationDetails(new JReq(), new Date())));

        JobApplications applications = repository.findByJobSeeker(jobSeeker);
        assertEquals(2, applications.size());
    }

}
