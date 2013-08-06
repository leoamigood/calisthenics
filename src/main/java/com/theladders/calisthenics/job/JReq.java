package com.theladders.calisthenics.job;

import com.theladders.calisthenics.actor.Recruiter;
import com.theladders.calisthenics.resume.Resume;
import com.theladders.confident.Maybe;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:56 PM
 */
public class JReq extends Job
{
  public JReq(String title,
              Recruiter recruiter)
  {
    super(title, recruiter);
  }

  @Override
  public boolean canApplyWith(Maybe<Resume> resume)
  {
    return resume.isSomething();
  }
}
