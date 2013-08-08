package com.theladders.calisthenics.report;

import com.theladders.calisthenics.job.application.JobApplications;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/8/13
 * Time: 10:00 AM
 */
public abstract class JobApplicationAggregator<K, V> implements Aggregator<K, V>
{
  protected JobApplications applications;

  public JobApplicationAggregator(JobApplications applications)
  {
    this.applications = applications;
  }

  public Iterable<Reportable> report()
  {
    List<Reportable> report = new ArrayList<>();
    for (Map.Entry<K, V> entry: apply().entrySet())
    {
      report.add(toReportable(entry));
    }

    return report;
  }

  private Reportable toReportable(final Map.Entry<K, V> entry)
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
}
