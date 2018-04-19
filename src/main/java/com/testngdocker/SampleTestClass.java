package main.java.com.testngdocker;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.testng.Assert;
import org.testng.annotations.*;
import com.saucelabs.ci.sauceconnect.SauceConnectFourManager;
import com.saucelabs.ci.sauceconnect.SauceTunnelManager;

import java.io.IOException;

public class SampleTestClass {
    private SauceTunnelManager SauceConnectFourManager;
    private BrowserMobProxyServer browserMobProxyServer;

    @BeforeClass
    public void SetUp() {
        SauceConnectFourManager = new SauceConnectFourManager(true);
        browserMobProxyServer = new BrowserMobProxyServer();
        browserMobProxyServer.setTrustAllServers(true);
        browserMobProxyServer.start(0);
        String host = "localhost";
        String port = String.valueOf(browserMobProxyServer.getPort());
        System.out.println(host + ":"+ port);
        try {
            SauceConnectFourManager.openConnection("benadryl", "1f5c74ff-010d-4fc8-a140-4a00cdf1b0d4",4445,
                    null, "-v --pac file:////Users/vemba/Documents/Java/java-docker-testng/browsermob.js", null, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestShouldPass() {
        Assert.assertEquals(1,1);
    }

    @Test
    public void TestShouldPassToo() {
        Assert.assertEquals(2, 2);
    }

    @AfterClass
    public void TearDown() {
        SauceConnectFourManager.closeTunnelsForPlan("benadryl", null, null);
        browserMobProxyServer.stop();
    }
}
