package com.theladders.calisthenics.util;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.matchers.GreaterThan;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * User: Leo Amigood <lamigud@theladders.com>
 * Date: 8/1/13
 * Time: 12:28 PM
 */
public class IdentityUtilTest
{
    public static class Id implements Identifier
    {
        public Id(Integer id){}
    }

    private class PrivateNonStaticClassId implements Identifier
    {
        public PrivateNonStaticClassId(Integer id){}
    }

    public static class MissingConstructorId implements Identifier{}

    @Test
    public void testGetId() throws Exception
    {
        Assert.assertNotNull(IdentityUtil.getId(Id.class));
    }

    @Test (expected = RuntimeException.class)
    public void testGetIdWithNoConstructorClass() throws Exception
    {
        IdentityUtil.getId(MissingConstructorId.class);
    }

    @Test (expected = RuntimeException.class)
    public void testGetIdWithPrivateClass() throws Exception
    {
        IdentityUtil.getId(PrivateNonStaticClassId.class);
    }
}
