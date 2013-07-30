package com.theladders.calisthenics.service;

import com.theladders.calisthenics.domain.*;
import com.theladders.calisthenics.job.JobApplicationRepository;
import com.theladders.calisthenics.job.JobRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/29/13
 * Time: 10:55 AM
 */
public class RecruitServiceTest
{
    RecruitService service;

    JobRepository jobRepo = Mockito.mock(JobRepository.class);
    JobApplicationRepository appRepo = Mockito.mock(JobApplicationRepository.class);

    Recruiter recruiter = new Recruiter();

    @Before
    public void setUp()
    {
        service = new RecruitService(jobRepo, appRepo);
    }

    @Test
    public void testPostOneJob() throws Exception
    {
        Job job = new ATS();
        Jobs jobs = new Jobs(job);
        Jobs posted = service.postJobs(recruiter, jobs);

        verify(jobRepo, times(1)).addJob(recruiter, job);

        assertEquals(1, posted.size());
    }

    @Test
    public void testPostMultipleJobs() throws Exception
    {
        Job ats = new ATS();
        Job jReq = new JReq();
        Jobs jobs = new Jobs(ats, jReq);
        Jobs posted = service.postJobs(recruiter, jobs);

        verify(jobRepo, times(1)).addJob(recruiter, ats);
        verify(jobRepo, times(1)).addJob(recruiter, jReq);
        verify(jobRepo, times(0)).addJob(recruiter, new ATS());

        assertEquals(2, posted.size());
    }

    @Test
    public void testGetPostedJobs()
    {
        service.getPostedJobs(recruiter);
        verify(jobRepo).findByRecruiter(recruiter);
    }

    @Test
    public void testGetOneJobApplicants()
    {
        Job jReq = new JReq();
        Jobs jobs = new Jobs(jReq);
        JobApplications applications = new JobApplications();

        JobSeeker seeker = new JobSeeker();
        applications.add(new JobApplication(seeker, new JobApplicationDetails(jReq, new Date())));

        when(jobRepo.findByRecruiter(recruiter)).thenReturn(jobs);
        when(appRepo.findByJob(jReq)).thenReturn(applications);
        assertEquals(1, service.getApplicants(recruiter).size());
        assertTrue(service.getApplicants(recruiter).contains(seeker));
    }

    @Test
    public void testGetMultipleJobsApplicants()
    {
        Job ats = new ATS();
        Job jReq = new JReq();
        Jobs jobs = new Jobs(ats, jReq);
        JobSeeker seeker = new JobSeeker();

        JobApplications atsApplications = new JobApplications(new JobApplication(seeker, new JobApplicationDetails(ats, new Date())));
        JobApplications jReqApplications = new JobApplications(new JobApplication(seeker, new JobApplicationDetails(jReq, new Date())));

        when(jobRepo.findByRecruiter(recruiter)).thenReturn(jobs);
        when(appRepo.findByJob(ats)).thenReturn(atsApplications);
        when(appRepo.findByJob(jReq)).thenReturn(jReqApplications);
        assertEquals(2, service.getJobApplications(recruiter).size());
        assertEquals(1, service.getApplicants(recruiter).size()); //same job seeker applied for ATS and jReq jobs

        assertTrue(service.getApplicants(recruiter).contains(seeker));
    }

}