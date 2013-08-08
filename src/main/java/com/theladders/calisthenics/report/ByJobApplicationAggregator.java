package com.theladders.calisthenics.report;

import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplications;
import org.apache.commons.collections.Factory;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/5/13
 * Time: 5:35 PM
 */
public class ByJobApplicationAggregator extends JobApplicationAggregator<Job, Integer>
{
  public ByJobApplicationAggregator(JobApplications applications)
  {
    super(applications);
  }

  @Override
  public Map<Job, Integer> apply()
  {
    Map<Job, Integer> totals = LazyMap.decorate(new HashMap(), new Factory()
    {
      @Override
      public Object create()
      {
        return 0;
      }
    });

    for (JobApplication application: applications){
      totals.put(application.job(), totals.get(application.job()) + 1);
    }

    return totals;
  }
}
