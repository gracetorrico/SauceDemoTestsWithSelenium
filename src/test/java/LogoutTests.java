import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

public class LogoutTests extends BaseTest
{
    @Test
    public void verifyUserCanLogout() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnHamburgerButton();
        homePage.clickOnLogoutLink();

        Assertions.assertTrue(loginPage.isLoginButtonDisplayed());
        Thread.sleep(1000);
    }
}
