package main.java.com.testngdocker;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import com.saucelabs.ci.sauceconnect.SauceConnectFourManager;
import com.saucelabs.ci.sauceconnect.SauceTunnelManager;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SampleTestClass {
    private SauceTunnelManager SauceConnectFourManager;
    private BrowserMobProxyServer browserMobProxyServer;
    private WebDriver driver;
    private  String username = System.getenv("CLOUD_TESTING_USERNAME");
    private String key = System.getenv("CLOUD_TESTING_KEY");
    private String filePath = "/usr/local/app/browsermob.js";

    @BeforeClass
    public void SetUp() {
        SauceConnectFourManager = new SauceConnectFourManager(true);
        browserMobProxyServer = new BrowserMobProxyServer();
        browserMobProxyServer.setTrustAllServers(true);
        browserMobProxyServer.start(9191);
        browserMobProxyServer.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        browserMobProxyServer.newHar("test");

        try {
            SauceConnectFourManager.openConnection(username, key,4445,
                    null, "-v --pac file://" + filePath,
                    null, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "latest");
        String url = "https://" + username + ":" + key + "@ondemand.saucelabs.com:443/wd/hub";
        try {
            driver = new RemoteWebDriver(new URL(url), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestShouldPass() {
        /**
         * Goes to Sauce Lab's guinea-pig page and prints title
         */

        driver.get("https://google.com");
        System.out.println("title of page is: " + driver.getTitle());
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
        SauceConnectFourManager.closeTunnelsForPlan(username, null, null);
        Har har = browserMobProxyServer.getHar();
        System.out.println("entered tear down");
        har.getLog().getEntries().removeIf(x->
                !(x.getRequest().getMethod().equals("POST")));
        for (HarEntry entry : har.getLog().getEntries()) {
            System.out.println(entry.getRequest().getUrl());
        }
        browserMobProxyServer.stop();
    }
}
