import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;
import utilities.DriverManager;

public class Checkout  extends  BaseTest
{
    @Test
    public void verifyCheckoutOverview() throws InterruptedException { //Verifica que se redirije a la página Checkou Overview
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Bike Light");
        Thread.sleep(1000);
        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Thread.sleep(1000);
        yourCartPage.clickOnCheckoutButton();

        CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver().driver);
        checkoutPage.setFirstNameTextBox("Ciro");
        checkoutPage.setLastNameTextBox("Albornoz");
        checkoutPage.setPostalCodeTextBox("2124");
        Thread.sleep(1000);
        checkoutPage.clickOnContinueButton();
        Thread.sleep(1000);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(checkoutOverviewPage.isCheckoutTitleDisplayed());
    }

    @Test
    public void verifyCorrectPrice() throws InterruptedException { //Verifica que la suma del precio es correcta
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        Thread.sleep(1000);
        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Thread.sleep(1000);
        yourCartPage.clickOnCheckoutButton();

        CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver().driver);
        checkoutPage.setFirstNameTextBox("Ciro");
        checkoutPage.setLastNameTextBox("Albornoz");
        checkoutPage.setPostalCodeTextBox("2124");
        Thread.sleep(1000);
        checkoutPage.clickOnContinueButton();
        Thread.sleep(3000);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(checkoutOverviewPage.isCheckoutTitleDisplayed());
        Assertions.assertEquals(yourCartPage.totalPrice(),checkoutOverviewPage.getSubTotal());
    }

    @Test
    public void verifyCompleteSale() throws InterruptedException { //Verifica que la venta se concreta
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        Thread.sleep(1000);
        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Thread.sleep(1000);
        yourCartPage.clickOnCheckoutButton();

        CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver().driver);
        checkoutPage.setFirstNameTextBox("Ciro");
        checkoutPage.setLastNameTextBox("Albornoz");
        checkoutPage.setPostalCodeTextBox("2124");
        Thread.sleep(1000);
        checkoutPage.clickOnContinueButton();
        Thread.sleep(1000);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(DriverManager.getDriver().driver);
        checkoutOverviewPage.clickOnFinishButton();
        Thread.sleep(1000);
        Assertions.assertTrue(checkoutOverviewPage.isCompleteLabelDisplayed());
    }

    @Test
    public void verifyContinueShoppingButton() throws InterruptedException //Verifica que el botón Continue Shopping regrese a la página inicial
    {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        Thread.sleep(1000);
        homePage.clickOnShoppingCartButton();
        Thread.sleep(1000);
        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        yourCartPage.clickOnContinueShoppingButton();
        Thread.sleep(1000);
        Assertions.assertTrue(homePage.pageTitleIsDisplayed());
    }
}
