package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

public class LoginPageObject extends BasePage {

    private final By usernameInput = By.cssSelector("input[name='username']");
    private final By passwordInput = By.cssSelector("input[name='password']");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorSpans = By.cssSelector(".oxd-input-field-error-message");
    private final By dashboardVerify = By.xpath("//span[@class='oxd-topbar-header-breadcrumb']/h6");
    private final By invalidError = By.xpath("//div[contains(@class,'oxd-alert-content--error')]/p");

    public LoginPageObject(WebDriver driver) {
        super(driver);
    }

    public void verifyOnLoginPage() {
        waitForVisible(usernameInput);
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    public void enterCredentials(String user, String pass) {
        type(usernameInput, user);
        type(passwordInput, pass);
    }

    public void clearUsernameAndCheckRequired() {
        clearField(usernameInput);
        Assert.assertEquals(driver.findElements(errorSpans).get(0).getText(), "Required");
    }

    public void clearPasswordAndCheckRequired() {
        clearField(passwordInput);
        Assert.assertEquals(driver.findElements(errorSpans).get(1).getText(), "Required");
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void verifyDashboardPageAfterLogin() {
        Assert.assertEquals(getText(dashboardVerify).trim(), "Dashboard");
    }

    public String viewInvalidError() {
        return getText(invalidError);
    }

    public void logoutPage() {
        click(By.className("oxd-userdropdown-name"));
        click(By.xpath("//a[text()='Logout']"));
    }
}
