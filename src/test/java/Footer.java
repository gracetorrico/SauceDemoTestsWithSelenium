import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

import java.time.Duration;

public class Footer extends BaseTest
{
    @Test
    public void clickOnFooterSuccessTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        Thread.sleep(1000);
        String social = "twitter";
        homePage.clickOnSocial(social);

        for (String windowHandle : DriverManager.getDriver().driver.getWindowHandles()) {
            DriverManager.getDriver().driver.switchTo().window(windowHandle);
        }

        Assertions.assertEquals(homePage.getExpectedURL(social), DriverManager.getDriver().driver.getCurrentUrl());
    }
}
