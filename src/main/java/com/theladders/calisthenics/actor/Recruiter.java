package com.theladders.calisthenics.actor;

import com.theladders.calisthenics.util.Identifier;
import com.theladders.calisthenics.util.IdentityUtil;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 7/15/13
 * Time: 5:23 PM
 */
public class Recruiter
{
  private Id id;
  private String name;

  public static class Id implements Identifier
  {
    private Integer id;

    public Id(Integer id)
    {
      this.id = id;
    }
  }

  public Recruiter(String name)
  {
    this.id = IdentityUtil.getId(Recruiter.Id.class);
    this.name = name;
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
