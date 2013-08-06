package com.theladders.calisthenics.actor;

import com.theladders.calisthenics.CalisthenicsTest;
import com.theladders.calisthenics.resume.BasicResume;
import com.theladders.calisthenics.resume.Resume;
import com.theladders.calisthenics.service.JobSeekerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/16/13
 * Time: 1:16 PM
 */
public class JobSeekerTest extends CalisthenicsTest
{
  Resume resume;

  @Before
  public void setUp()
  {
    resume = new BasicResume();
  }

  @Test
  public void testIsOwner() throws Exception
  {
    jobSeeker.addResume(resume);

    assertTrue(jobSeeker.isOwner(resume));
    assertFalse(jobSeeker.isOwner(new BasicResume()));
    assertFalse(jobSeeker.isOwner(null));
  }

  @Test
  public void testApplyTo()
  {
    JobSeekerService service = Mockito.mock(JobSeekerService.class);
    jobSeeker.applyTo(resume, ats, service);

    verify(service, times(1)).apply(jobSeeker, resume, ats);
  }

  @Test
  public void testIdentity()
  {
    JobSeeker jobSeeker1 = new JobSeeker("John Smith");
    assertNotNull(jobSeeker1.id());
    assertEquals("John Smith", jobSeeker1.name());

    JobSeeker jobSeeker2 = new JobSeeker("John Smith");
    assertNotNull(jobSeeker2.id());
    assertEquals("John Smith", jobSeeker2.name());

    assertFalse(jobSeeker1.equals(jobSeeker2));
    assertFalse(jobSeeker1.id().equals(jobSeeker2.id()));
    assertTrue(jobSeeker1.name().equals(jobSeeker2.name()));
  }
}
