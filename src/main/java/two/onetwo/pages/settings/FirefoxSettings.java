package two.onetwo.pages.settings;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import two.onetwo.pages.data.BrowserModeData;
import two.onetwo.pages.exceptions.ModeNotSupportedException;

public class FirefoxSettings implements IBrowserSettings {

    private final String mode = System.getProperty("mode").toUpperCase();

    public AbstractDriverOptions<?> settings() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        BrowserModeData modeData = BrowserModeData.valueOf(mode);

        switch (modeData) {
            case HEADLESS:
                return firefoxOptions.addArguments("--headless");
            case FULLSCREEN:
                return firefoxOptions.addArguments("--start-fullscreen");
            case KIOSK:
                return firefoxOptions.addArguments("--kiosk");
        }
        throw new ModeNotSupportedException(mode);
    }
}
