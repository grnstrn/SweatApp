package com.example.sweatapp;

import org.junit.Assert;
import org.junit.Test;

public class SimpleTest {
    @Test
    public void test(){
        int x = 10;
        int y = 12;

        Assert.assertEquals(22, x + y);
        Assert.assertEquals(120, x * y);
    }
}
