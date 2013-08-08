package com.theladders.calisthenics.report;

import com.theladders.calisthenics.actor.Recruiter;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplications;
import org.apache.commons.collections.Factory;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/8/13
 * Time: 9:59 AM
 */
public class ByRecruiterAggregator extends JobApplicationAggregator<Recruiter, Integer>
{
  public ByRecruiterAggregator(JobApplications applications)
  {
    super(applications);
  }

  @Override
  public Map<Recruiter, Integer> apply()
  {
    Map<Recruiter, Integer> totals = LazyMap.decorate(new HashMap(), new Factory()
    {
      @Override
      public Object create()
      {
        return 0;
      }
    });

    for (JobApplication application: applications){
      totals.put(application.job().postedBy(), totals.get(application.job().postedBy()) + 1);
    }

    return totals;
  }
}
