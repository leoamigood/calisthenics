package com.theladders.calisthenics.report;

import com.theladders.calisthenics.job.Job;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/6/13
 * Time: 9:20 AM
 */
public class ByJobAggregatorTest extends ReportTest
{
  @Before
  public void setUp()
  {
    aggregator = new ByJobApplicationAggregator(applications);
  }

  @Test
  public void testAggregate() throws Exception
  {
    Map<Job, Integer> map = aggregator.apply();
    assertEquals(1, map.get(ats).intValue());
    assertEquals(3, map.get(jReq).intValue());
    assertEquals(2, map.get(atsJobFromChris).intValue());
    assertEquals(3, map.size());
  }
}
