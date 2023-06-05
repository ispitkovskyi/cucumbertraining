import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

/**
 * Created by ispitkovskyi on 6/19/2016.
 */

@Resource.Classpath("environment.properties")
public class EnvironmentProperties {
    private static EnvironmentProperties environmentProperties;

    private EnvironmentProperties() {
        PropertyLoader.populate(this);
    }

    public static EnvironmentProperties get() {
        if (environmentProperties == null) {
            environmentProperties = new EnvironmentProperties();
        }
        return environmentProperties;
    }

    @Property("browser")
    public String browser;

    @Property("language")
    public String language;

    @Property("siteHostname")
    public String siteHostname;

    public String getBrowser(){return browser;}
    public String getSiteHostname(){return siteHostname;}
    public String getLanguage(){return language;}


    public String getServerUrl() {
        return String.format("http://%s", getSiteHostname());
    }

}

