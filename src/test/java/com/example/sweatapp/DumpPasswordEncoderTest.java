package com.example.sweatapp;

import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class DumpPasswordEncoderTest extends TestCase {

    public void testEncode() {
        DumpPasswordEncoder encoder = new DumpPasswordEncoder();
        Assert.assertEquals("secret: 'mypwd'",encoder.encode("mypwd"));

        Assert.assertThat(encoder.encode("mypwd"), Matchers.containsString("mypwd"));
    }
}