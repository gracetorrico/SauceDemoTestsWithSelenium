import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.LoginPage;
import pages.YourCartPage;
import utilities.DriverManager;

import java.util.List;

public class Examen extends BaseTest{

    @Test
    public void testingExam() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Bike Light");

        homePage.clickOnShoppingCartButton();
        Thread.sleep(1000);

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Bike Light"));

        Assertions.assertTrue(yourCartPage.isPriceDisplayed("$49.99"));
        Assertions.assertTrue(yourCartPage.isPriceDisplayed("$9.99"));

        Assertions.assertTrue(yourCartPage.isProductPriceRight("Sauce Labs Fleece Jacket","$49.99"));
        Assertions.assertTrue(yourCartPage.isProductPriceRight("Sauce Labs Bike Light","$9.99"));
        Assertions.assertEquals("2", yourCartPage.getNumberOnShoppingCartBadgeButton());

        yourCartPage.removeProduct("Sauce Labs Fleece Jacket");
        yourCartPage.removeProduct("Sauce Labs Bike Light");
        Thread.sleep(2000);

        Assertions.assertFalse(yourCartPage.shoppingCartBadgeIsDisplayed());
        Assertions.assertFalse(yourCartPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
        Assertions.assertFalse(yourCartPage.isProductDisplayed("Sauce Labs Bike Light"));
    }
}
