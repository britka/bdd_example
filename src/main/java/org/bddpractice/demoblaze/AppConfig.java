package org.bddpractice.demoblaze;

import org.aeonbits.owner.Config;

/**
 * @author sbryt on 01/04/2021
 */
@Config.Sources({
        "classpath:app.properties"
})
public interface AppConfig extends Config {
    @Key("user.name")
    String user_name();

    @Key("user.pass")
    String user_pass();

    @Key("app.url")
    String app_url();
}
