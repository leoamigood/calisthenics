package com.theladders.calisthenics.report;

import com.theladders.calisthenics.job.Job;
import com.theladders.calisthenics.job.application.JobApplication;
import com.theladders.calisthenics.job.application.JobApplications;
import org.apache.commons.collections.Factory;
import org.apache.commons.collections.map.LazyMap;

import java.util.*;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/5/13
 * Time: 5:35 PM
 */
public class ByJobAggregator implements Aggregator<Job, Integer>
{
  private JobApplications applications;

  public ByJobAggregator(JobApplications applications)
  {
    this.applications = applications;
  }

  public Iterable<Reportable> report()
  {
    List<Reportable> report = new ArrayList<>();
    for (final Map.Entry<Job, Integer> entry: apply().entrySet())
    {
      report.add(toReportable(entry));
    }

    return report;
  }

  private Reportable toReportable(final Map.Entry<Job, Integer> entry)
  {
    return new Reportable()
    {
      @Override
      public Iterator<String> chunks()
      {
        return new ArrayList<String>() {
          {
            add(entry.getKey().toString());
            add(entry.getValue().toString());
          }
        }.iterator();
      }
    };
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
