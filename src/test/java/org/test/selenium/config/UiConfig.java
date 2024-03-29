package org.test.selenium.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:ui_config.properties"})
public interface UiConfig extends Config {
    @Key("base.url")
    String baseUrl();

    @Key("admin.username")
    @DefaultValue("admin")
    String username();

    @Key("admin.password")
    String password();
}
