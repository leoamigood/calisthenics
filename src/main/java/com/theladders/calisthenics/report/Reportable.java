package com.theladders.calisthenics.report;

import java.util.Iterator;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/5/13
 * Time: 6:00 PM
 */
public interface Reportable
{
  public Iterator<String> chunks();
}
