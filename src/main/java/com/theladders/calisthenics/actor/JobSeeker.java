package com.theladders.calisthenics.actor;

import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.resume.Resume;
import com.theladders.calisthenics.resume.Resumes;
import com.theladders.calisthenics.service.JobSeekerService;
import com.theladders.calisthenics.util.Identifier;
import com.theladders.calisthenics.util.IdentityUtil;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:23 PM
 */
public class JobSeeker
{
  private Id id;
  private String name;

  private Resumes resumes = new Resumes();

  public static class Id implements Identifier
  {
    private Integer id;

    public Id(Integer id)
    {
      this.id = id;
    }
  }

  public JobSeeker(String name)
  {
    this.id = IdentityUtil.getId(JobSeeker.Id.class);
    this.name = name;
  }

  public JobSeeker(String name, Resume resume)
  {
    this(name);
    addResume(resume);
  }

  public JobApplication applyTo(Resume resume,
                                Job job,
                                JobSeekerService applier)
  {
    return applier.apply(this, resume, job);
  }

  public boolean addResume(Resume resume)
  {
    return resumes.add(resume);
  }

  public boolean isOwner(Resume resume)
  {
    return resumes.contains(resume);
  }

  public Id id()
  {
    return id;
  }

  public String name()
  {
    return name;
  }

  @Override
  public String toString()
  {
    return id.id + " " + name;
  }
}
