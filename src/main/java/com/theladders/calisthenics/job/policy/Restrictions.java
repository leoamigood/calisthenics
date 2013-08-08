package com.theladders.calisthenics.job.policy;

import java.util.HashSet;
import java.util.Set;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/31/13
 * Time: 11:31 AM
 */
public class Restrictions
{
  private Set<Restriction> restrictions = new HashSet<>();

  public boolean isNone()
  {
    return restrictions.isEmpty();
  }

  public void add(final Restriction restriction)
  {
    restrictions.add(restriction);
  }
}
