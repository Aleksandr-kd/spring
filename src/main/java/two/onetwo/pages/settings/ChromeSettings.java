package two.onetwo.pages.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import two.onetwo.pages.data.BrowserModeData;
import two.onetwo.pages.exceptions.ModeNotSupportedException;

public class ChromeSettings implements IBrowserSettings {

    private final String mode = System.getProperty("mode").toUpperCase();

    public AbstractDriverOptions<?> settings() {
        ChromeOptions chromeOptions = new ChromeOptions();
        BrowserModeData modeData = BrowserModeData.valueOf(mode);

        switch (modeData) {
            case HEADLESS:
                return chromeOptions.addArguments("--headless=new");
            case FULLSCREEN:
                return chromeOptions.addArguments("--start-fullscreen");
            case KIOSK:
                return chromeOptions.addArguments("--kiosk");
        }
        throw new ModeNotSupportedException(mode);
    }
}
