package main.java.com.testngdocker;

import org.testng.Assert;
import org.testng.annotations.*;

public class SampleTestClass {
    @Test
    public void TestShouldPass() {
        Assert.assertEquals(1,1);
    }

    @Test
    public void TestShouldPassToo() {
        Assert.assertEquals(2, 2);
    }
}
