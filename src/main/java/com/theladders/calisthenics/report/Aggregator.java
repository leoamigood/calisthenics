package com.theladders.calisthenics.report;

import java.util.Map;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/5/13
 * Time: 5:31 PM
 */
public interface Aggregator<K, V>
{
  public Map<K, V> apply();
  public Iterable<Reportable> report();
}
