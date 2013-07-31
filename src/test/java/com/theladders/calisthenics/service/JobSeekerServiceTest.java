package com.theladders.calisthenics.service;

import com.theladders.calisthenics.actor.JobSeeker;
import com.theladders.calisthenics.job.ATS;
import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplicationDetails;
import com.theladders.calisthenics.job.application.JobApplications;
import com.theladders.calisthenics.job.application.SavedJobApplication;
import com.theladders.calisthenics.job.policy.ResumePolicy;
import com.theladders.calisthenics.repo.JobApplicationRepository;
import com.theladders.calisthenics.resume.BasicResume;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
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
        when(appRepo.findSaved(jobSeeker, job)).thenReturn(new JobApplications());

        service.saveJobApplication(jobSeeker, job);
        verify(appRepo, times(1)).save(any(SavedJobApplication.class));

    }

    @Test
    public void testSaveExistingJobApplication() throws Exception
    {
        Job job = new ATS();
        JobApplicationDetails details = new JobApplicationDetails(job, new Date());
        when(appRepo.findSaved(jobSeeker, job)).thenReturn(new JobApplications(new JobApplication(jobSeeker, details)));

        service.saveJobApplication(jobSeeker, job);
        verify(appRepo, times(0)).save(any(SavedJobApplication.class));
    }

    @Test
    public void testApply()
    {
        assertNotNull(service.apply(jobSeeker, new BasicResume(), new ATS(), new ResumePolicy()));
    }

}
