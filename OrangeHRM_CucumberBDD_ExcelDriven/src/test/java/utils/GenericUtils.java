package utils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class GenericUtils {
    private final WebDriver driver;

    public GenericUtils(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Switches to the first window handle that is not the provided parent handle.
     * If parentHandle is null it will switch to any window that isn't the current.
     */
    public void switchToChildWindow(String parentHandle) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                return;
            }
        }
    }

    /**
     * Helper: switch to last opened window
     */
    public void switchToLastWindow() {
        String last = null;
        for (String h : driver.getWindowHandles()) {
            last = h;
        }
        if (last != null) driver.switchTo().window(last);
    }

    public void maximizeCurrentWindow() {
        driver.manage().window().maximize();
    }
}
