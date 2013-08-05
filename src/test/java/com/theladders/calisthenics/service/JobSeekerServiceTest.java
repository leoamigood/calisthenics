package com.theladders.calisthenics.service;

import com.theladders.calisthenics.CalisthenicsTest;
import com.theladders.calisthenics.job.Jobs;
import com.theladders.calisthenics.job.application.*;
import com.theladders.calisthenics.job.policy.JobApplicationPolicy;
import com.theladders.calisthenics.job.policy.ResumePolicy;
import com.theladders.calisthenics.resume.BasicResume;
import com.theladders.calisthenics.resume.Resume;
import com.theladders.confident.Maybe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 12:45 PM
 */
public class JobSeekerServiceTest extends CalisthenicsTest
{
    JobSeekerService service;

    JobApplicationRepository appRepo = Mockito.mock(JobApplicationRepository.class);

    @Before
    public void setUp()
    {
        service = new JobSeekerService(appRepo, Maybe.<JobApplicationPolicy>just(new ResumePolicy()));
    }

    @Test
    public void testSaveNonExistingJobApplication() throws Exception
    {
        JobApplicationDetails details = new JobApplicationDetails(ats, new Date());
        when(appRepo.findByJobSeeker(jobSeeker)).thenReturn(new JobApplications());

        service.saveJobApplication(jobSeeker, ats);
        verify(appRepo, times(1)).save(any(SavedJobApplication.class));

    }

    @Test
    public void testSaveExistingJobApplication() throws Exception
    {
        JobApplicationDetails details = new JobApplicationDetails(ats, new Date());
        when(appRepo.findByJobSeeker(jobSeeker)).thenReturn(new JobApplications(new JobApplication(jobSeeker, details)));

        service.saveJobApplication(jobSeeker, ats);
        verify(appRepo, times(0)).save(any(SavedJobApplication.class));
    }

    @Test
    public void testListJobsSaved()
    {
        service = new JobSeekerService(new InMemoryJobApplicationRepository(), Maybe.<JobApplicationPolicy>just(new ResumePolicy()));
        service.saveJobApplication(jobSeeker, ats);
        Jobs jobs = service.getJobsSaved(jobSeeker);
        assertEquals(1, jobs.size());
        assertTrue(jobs.contains(ats));
        assertFalse(jobs.contains(jReq));
    }

    @Test
    public void testListJobsApplied()
    {
        service = new JobSeekerService(new InMemoryJobApplicationRepository(), Maybe.<JobApplicationPolicy>nothing());
        service.apply(jobSeeker, new BasicResume(), jReq);
        Jobs jobs = service.jobsAppliedBy(jobSeeker);
        assertEquals(1, jobs.size());
        assertFalse(jobs.contains(ats));
        assertTrue(jobs.contains(jReq));
    }

    @Test
    public void testSuccessfulApply()
    {
        BasicResume resume = new BasicResume();
        jobSeeker.addResume(resume);
        JobApplication application = service.apply(jobSeeker, resume, ats);

        assertNotNull(application);
        assertTrue(application.status().isAccepted());
        assertFalse(application.status().isSaved());
    }

    @Test
    public void testSuccessfulApplyWithJReq()
    {
        BasicResume resume = new BasicResume();
        jobSeeker.addResume(resume);
        JobApplication application = service.apply(jobSeeker, resume, jReq);

        assertNotNull(application);
        assertTrue(application.status().isAccepted());
        assertFalse(application.status().isSaved());
    }

    @Test
    public void testDeniedApply()
    {
        JobApplication application = service.apply(jobSeeker, new BasicResume(), ats);
        assertNotNull(application);
        assertFalse(application.status().isAccepted());
        assertFalse(application.status().isSaved());
    }

    @Test
    public void testDeniedApplyWithJReq()
    {
        Resume incomplete = new Resume()
        {
            @Override
            public boolean isCompleted()
            {
                return false;
            }
        };

        jobSeeker.addResume(incomplete);
        JobApplication application = service.apply(jobSeeker, incomplete, jReq);

        assertNotNull(application);
        assertFalse(application.status().isAccepted());
        assertFalse(application.status().isSaved());
    }

    @Test
    public void testGetJobApplications()
    {
        setUpRepositoryWithMultiDatedApplications();

        BasicResume resume = new BasicResume();
        jobSeeker.addResume(resume);

        assertEquals(1, service.jobsAppliedBy(getYesterdayDate()).size());
        assertEquals(1, service.jobsAppliedBy(new Date()).size());
        assertEquals(0, service.jobsAppliedBy(getTwoDaysAgoDate()).size());
    }

    private void setUpRepositoryWithMultiDatedApplications()
    {
        InMemoryJobApplicationRepository repository = new InMemoryJobApplicationRepository();

        CompletedJobApplication yesterdaysApplication = mock(CompletedJobApplication.class);
        CompletedJobApplication todayApplication = mock(CompletedJobApplication.class);

        when(yesterdaysApplication.date()).thenReturn(getYesterdayDate());
        when(todayApplication.date()).thenReturn(getEarlierToday());
        when(yesterdaysApplication.applicant()).thenReturn(jobSeeker);
        when(todayApplication.applicant()).thenReturn(jobSeeker);

        repository.save(yesterdaysApplication);
        repository.save(todayApplication);

        service = new JobSeekerService(repository, Maybe.<JobApplicationPolicy>just(new ResumePolicy()));
    }

}
