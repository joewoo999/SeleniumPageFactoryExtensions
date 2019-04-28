package com.github.pfextentions.core;

import com.github.pfextentions.common.Resources;
import com.github.pfextentions.common.config.ConfigFactory;
import com.github.pfextentions.common.config.ConfigKey;
import com.github.pfextentions.core.driverContext.Driver;
import com.github.pfextentions.core.driverContext.drivers.Chrome;
import com.github.pfextentions.core.driverContext.drivers.Firefox;
import com.github.pfextentions.core.driverContext.drivers.InternetExplorer;
import org.openqa.selenium.remote.BrowserType;


public class PropertiesConfig implements BrowserConfig {

    @ConfigKey("browserType")
    private String browserType = BrowserType.CHROME;

    @ConfigKey("isHeadless")
    private boolean isHeadless = false;

    @ConfigKey("IEDriver")
    private String IEDriver = "src/test/resources/IEDriverServer.exe";

    @ConfigKey("ChromeDriver")
    private String chromeDriver = "src/test/resources/chromedriver.exe";

    @ConfigKey("GeckoDriver")
    private String firefoxDriver = "src/test/resources/geckodriver.exe";

    @ConfigKey("chromeBinary")
    private String chromeBinary;

    @ConfigKey("firefoxBinary")
    private String firefoxBinary;

    @ConfigKey("startMaximized")
    private boolean startMaximized = true;

    @ConfigKey("PageLoadTime")
    private int pageLoadTime = 60;

    @ConfigKey("ImplicitlyWaitTime")
    private int implicitlyWaitTime = 5;

    @ConfigKey("defaultDownloadDir")
    private String defaultDownloadDir = "C:\\";

    private String browserBinary;

    private Class<? extends Driver> driverClass;

    public PropertiesConfig() {
        ConfigFactory.init("config.properties", this);

        if (isChrome()) {
            setProperty(CHROME_DRIVER_PROPERTY, chromeDriver, chromeBinary, Chrome.class);
        } else if (isFirefox()) {
            setProperty(FIREFOX_DRIVER_PROPERTY, firefoxDriver, firefoxBinary, Firefox.class);
        } else if (isIE()) {
            setProperty(IE_DRIVER_PROPERTY, IEDriver, null, InternetExplorer.class);
        } else {
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    @Override
    public String browserType() {
        return browserType;
    }

    @Override
    public boolean isHeadless() {
        return isHeadless;
    }

    @Override
    public String browserBinary() {
        return browserBinary;
    }

    @Override
    public Class<? extends Driver> driverClass() {
        return driverClass;
    }

    @Override
    public int pageLoadTime() {
        return pageLoadTime;
    }

    @Override
    public int implicitlyWaitTime() {
        return implicitlyWaitTime;
    }

    @Override
    public boolean startMaximized() {
        return startMaximized;
    }

    @Override
    public String defaultDownloadDir() {
        return defaultDownloadDir;
    }


    private void setProperty(String browserProperty, String browserDriver,
                             String browserBinary, Class<? extends Driver> driverClass) {

        System.setProperty(browserProperty, Resources.getFilePath(browserDriver));
        this.browserBinary = Resources.getFilePath(browserBinary);
        this.defaultDownloadDir = Resources.getDirPath(defaultDownloadDir);
        this.driverClass = driverClass;
    }

    public void setBrowserType(String browserType){
        this.browserType = browserType;
    }

}
