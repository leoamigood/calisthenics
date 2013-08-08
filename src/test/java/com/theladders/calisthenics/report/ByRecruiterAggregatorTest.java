package com.theladders.calisthenics.report;

import com.theladders.calisthenics.actor.Recruiter;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/8/13
 * Time: 9:59 AM
 */
public class ByRecruiterAggregatorTest extends ReportTest
{
  @Before
  public void setUp()
  {
    aggregator = new ByRecruiterAggregator(applications);
  }

  @Test
  public void testAggregate() throws Exception
  {
    Map<Recruiter, Integer> map = aggregator.apply();
    assertEquals(4, map.get(recruiter).intValue());
    assertEquals(0, map.get(new Recruiter("Sam Recruiter")).intValue());
  }
}
