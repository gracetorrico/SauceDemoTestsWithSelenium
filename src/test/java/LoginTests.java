import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

public class LoginTests extends BaseTest {

    @Test
    public void loginSuccessTest()  {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(homePage.pageTitleIsDisplayed());
    }

    @Test
    public void loginFailed(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("wrong_user");
        loginPage.setPasswordTextBox("wrong_password");
        loginPage.clickOnLoginButton();

        Assertions.assertTrue(loginPage.isErrorTextDisplayed("Epic sadface: Username and password do not match any user in this service"));
    }


}
