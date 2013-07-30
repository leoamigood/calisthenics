package com.theladders.calisthenics.service;

import com.theladders.calisthenics.domain.*;
import com.theladders.calisthenics.job.JobApplicationRepository;
import com.theladders.calisthenics.job.JobRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

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

    JobRepository jobRepo = Mockito.mock(JobRepository.class);
    JobApplicationRepository appRepo = Mockito.mock(JobApplicationRepository.class);

    @Before
    public void setUp()
    {
        service = new JobSeekerService(jobRepo, appRepo);
    }

    @Test
    public void testSaveNonExistingJobApplication() throws Exception
    {
        Job ats = new ATS();
        JobApplicationDetails details = new JobApplicationDetails(ats, new Date());
        when(appRepo.find(ats, jobSeeker)).thenReturn(new JobApplications());

        JobApplications applications = service.saveJobApplication(jobSeeker, ats);
        verify(appRepo, times(1)).saveJobApplication(jobSeeker, ats);

    }

    @Test
    public void testSaveExistingJobApplication() throws Exception
    {
        Job ats = new ATS();
        JobApplicationDetails details = new JobApplicationDetails(ats, new Date());
        when(appRepo.find(ats, jobSeeker)).thenReturn(new JobApplications(new JobApplication(jobSeeker, details)));

        JobApplications applications = service.saveJobApplication(jobSeeker, ats);
        verify(appRepo, times(0)).saveJobApplication(jobSeeker, ats);
    }

}
