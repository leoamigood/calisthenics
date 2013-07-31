package com.theladders.calisthenics.job;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
import com.theladders.calisthenics.job.application.JobApplications;
import com.theladders.calisthenics.job.application.SavedJobApplication;
import com.theladders.calisthenics.repo.InMemoryJobApplicationRepository;
import com.theladders.calisthenics.repo.JobApplicationRepository;
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
public class InMemoryJobApplicationRepositoryTest
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
        assertNotNull(repository.find(new ATS()));
        assertEquals(0, repository.find(new ATS()).size());
        assertEquals(0, repository.find(new JobSeeker()).size());
    }

    @Test
    public void testFindByJob() throws Exception
    {
        Job ats = new ATS();
        Job jReq = new JReq();
        JobSeeker jobSeeker = new JobSeeker();
        repository.save(new SavedJobApplication(jobSeeker, new JobApplicationDetails(ats, new Date())));
        repository.save(new SavedJobApplication(jobSeeker, new JobApplicationDetails(jReq, new Date())));

        JobApplications atsApplications = repository.find(ats);
        assertEquals(1, atsApplications.size());

        JobApplications jReqApplications = repository.find(jReq);
        assertEquals(1, jReqApplications.size());
    }

    @Test
    public void testFindByJobSeeker() throws Exception
    {
        JobSeeker jobSeeker = new JobSeeker();
        repository.save(new SavedJobApplication(jobSeeker, new JobApplicationDetails(new ATS(), new Date())));
        repository.save(new SavedJobApplication(jobSeeker, new JobApplicationDetails(new JReq(), new Date())));
        JobApplications applications = repository.find(jobSeeker);
        assertEquals(2, applications.size());
    }

    @Test
    public void testFindSaved() throws Exception
    {
        JobSeeker jobSeeker = new JobSeeker();
        repository.save(new SavedJobApplication(jobSeeker, new JobApplicationDetails(new ATS(), new Date())));
        repository.save(new SavedJobApplication(jobSeeker, new JobApplicationDetails(new JReq(), new Date())));

        JobApplications applications = repository.find(jobSeeker);
        assertEquals(2, applications.size());
    }

}
