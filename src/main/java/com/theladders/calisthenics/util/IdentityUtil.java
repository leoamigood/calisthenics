package com.theladders.calisthenics.util;

import java.lang.reflect.Constructor;
import java.util.Random;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 12:07 PM
 */
public class IdentityUtil
{
    private static Random random = new Random();

    public static <T extends Identifier> T getId(Class<T> idClass)
    {
        try {
            Constructor<? extends Identifier> constructor = idClass.getConstructor(Integer.class);
            return (T) constructor.newInstance(random.nextInt(Integer.MAX_VALUE));
        } catch (Exception e) {
            throw new RuntimeException("Unable to create identifier for " + idClass.getName(), e);
        }
    }
}
