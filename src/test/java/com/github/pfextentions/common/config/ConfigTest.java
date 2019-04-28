package com.github.pfextentions.common.config;

public class ConfigTest {
    @ConfigKey("browserType")
    public String browserType;

    @ConfigKey("isHeadless")
    public boolean isHeadless;

    @ConfigKey("IEDriver")
    public String IEDriver;

    @ConfigKey("ChromeDriver")
    public String chromeDriver;

    @ConfigKey("GeckoDriver")
    public String firefoxDriver;
}
