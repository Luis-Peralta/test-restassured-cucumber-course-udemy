package test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestOne {

    @Test
    public void testOne(){
        int a = 7;
        int b = 6;
        Assert.assertTrue(a > b, "Number b is greater than a.");
    }

}
