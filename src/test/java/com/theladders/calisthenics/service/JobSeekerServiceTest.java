package com.theladders.calisthenics.service;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.JReq;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
import com.theladders.calisthenics.job.application.JobApplications;
import com.theladders.calisthenics.job.application.SavedJobApplication;
import com.theladders.calisthenics.job.policy.ResumePolicy;
import com.theladders.calisthenics.repo.JobApplicationRepository;
import com.theladders.calisthenics.resume.BasicResume;
import com.theladders.calisthenics.resume.Resume;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/30/13
 * Time: 12:45 PM
 */
public class JobSeekerServiceTest
{
    JobSeekerService service;
    JobSeeker jobSeeker = new JobSeeker();

    JobApplicationRepository appRepo = Mockito.mock(JobApplicationRepository.class);

    @Before
    public void setUp()
    {
        service = new JobSeekerService(appRepo);
    }

    @Test
    public void testSaveNonExistingJobApplication() throws Exception
    {
        Job job = new ATS();
        JobApplicationDetails details = new JobApplicationDetails(job, new Date());
        when(appRepo.find(jobSeeker)).thenReturn(new JobApplications());

        service.saveJobApplication(jobSeeker, job);
        verify(appRepo, times(1)).save(any(SavedJobApplication.class));

    }

    @Test
    public void testSaveExistingJobApplication() throws Exception
    {
        Job job = new ATS();
        JobApplicationDetails details = new JobApplicationDetails(job, new Date());
        when(appRepo.find(jobSeeker)).thenReturn(new JobApplications(new JobApplication(jobSeeker, details)));

        service.saveJobApplication(jobSeeker, job);
        verify(appRepo, times(0)).save(any(SavedJobApplication.class));
    }

    @Test
    public void testSuccessfulApply()
    {
        BasicResume resume = new BasicResume();
        jobSeeker.addResume(resume);
        JobApplication application = service.apply(jobSeeker, resume, new ATS(), new ResumePolicy());

        assertNotNull(application);
        assertTrue(application.status().isAccepted());
        assertFalse(application.status().isSaved());
    }

    @Test
    public void testSuccessfulApplyWithJReq()
    {
        BasicResume resume = new BasicResume();
        jobSeeker.addResume(resume);
        JobApplication application = service.apply(jobSeeker, resume, new JReq(), new ResumePolicy());

        assertNotNull(application);
        assertTrue(application.status().isAccepted());
        assertFalse(application.status().isSaved());
    }

    @Test
    public void testDeniedApply()
    {
        JobApplication application = service.apply(jobSeeker, new BasicResume(), new ATS(), new ResumePolicy());
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
        JobApplication application = service.apply(jobSeeker, incomplete, new JReq(), new ResumePolicy());

        assertNotNull(application);
        assertFalse(application.status().isAccepted());
        assertFalse(application.status().isSaved());
    }

}
