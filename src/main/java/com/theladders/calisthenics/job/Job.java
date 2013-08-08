package com.theladders.calisthenics.job;

import com.theladders.calisthenics.actor.Recruiter;
import com.theladders.calisthenics.resume.Resume;
import com.theladders.calisthenics.util.Identifier;
import com.theladders.calisthenics.util.IdentityUtil;
import com.theladders.confident.Maybe;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:23 PM
 */
public abstract class Job
{
  private Id id;
  private String title;

  private Recruiter recruiter;

  public static class Id implements Identifier
  {
    private Integer id;

    public Id(Integer id)
    {
      this.id = id;
    }
  }

  public Job(String title, Recruiter recruiter)
  {
    this.id = IdentityUtil.getId(Id.class);
    this.title = title;
    this.recruiter = recruiter;
  }

  public Id id()
  {
    return id;
  }

  public String title()
  {
    return title;
  }

  public Recruiter postedBy()
  {
    return recruiter;
  }

  public abstract boolean canApplyWith(Maybe<Resume> resume);

  @Override
  public String toString()
  {
    return id.id + " " + title;
  }
}
