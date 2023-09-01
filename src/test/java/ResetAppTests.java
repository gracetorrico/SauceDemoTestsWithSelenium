import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

public class ResetAppTests extends BaseTest {

    @Test
    public void verifyCorrectResetAppTests() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        Thread.sleep(1000);

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Bike Light");

        homePage.clickOnHamburgerButton();
        homePage.clickOnResetAppState();
        Thread.sleep(2000);

        Assertions.assertFalse(homePage.isShoppingCartBadgeIsDisplayed());
        Assertions.assertFalse(homePage.isRemovedButtonDisplayed("Sauce Labs Fleece Jacket"));
        Assertions.assertFalse(homePage.isRemovedButtonDisplayed("Sauce Labs Bike Light"));
        Thread.sleep(1000);
    }
}
