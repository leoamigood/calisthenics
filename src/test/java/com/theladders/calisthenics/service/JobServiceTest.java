package com.theladders.calisthenics.service;

import com.theladders.calisthenics.domain.*;
import com.theladders.calisthenics.job.JobRepository;
import com.theladders.calisthenics.service.filter.JobFilters;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/29/13
 * Time: 10:55 AM
 */
public class JobServiceTest
{
    JobService service;
    JobRepository repository = Mockito.mock(JobRepository.class);

    Recruiter recruiter = new Recruiter();

    @Before
    public void setUp()
    {
        service = new JobService(repository);
    }

    @Test
    public void testPostOneJob() throws Exception
    {
        Job job = new ATS();
        Jobs jobs = new Jobs(job);
        Jobs posted = service.postJobs(recruiter, jobs);

        verify(repository, times(1)).addJob(recruiter, job);

        assertEquals(1, posted.size());
    }

    @Test
    public void testPostMultipleJobs() throws Exception
    {
        Job ats = new ATS();
        Job jReq = new JReq();
        Jobs jobs = new Jobs(ats, jReq);
        Jobs posted = service.postJobs(recruiter, jobs);

        verify(repository, times(1)).addJob(recruiter, ats);
        verify(repository, times(1)).addJob(recruiter, jReq);
        verify(repository, times(0)).addJob(recruiter, new ATS());

        assertEquals(2, posted.size());
    }

    @Test
    public void testListJobs()
    {
        service.getJobs(recruiter);
        verify(repository).findByRecruiter(recruiter);
    }

    @Test
    @Ignore
    public void testListJobApplications()
    {

    }
}
