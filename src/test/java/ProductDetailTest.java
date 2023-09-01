import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import utilities.DriverManager;

public class ProductDetailTest extends BaseTest
{
    @Test
    public void verifyDataIsSameOnMainPageAndProductPage() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        String productName = "Sauce Labs Backpack";

        String mainPageProductName = homePage.getProductName(productName);
        String mainPageProductPrice = homePage.getProductPrice(productName);
        homePage.clickOnProduct(productName);
        Thread.sleep(2000);

        ProductPage productPage = new ProductPage(DriverManager.getDriver().driver);
        String productPageProductName = productPage.getProductName(productName);
        String productPageProductPrice = productPage.getProductPrice(productName);

        Assertions.assertEquals(mainPageProductName, productPageProductName);
        Assertions.assertEquals(mainPageProductPrice, productPageProductPrice);
    }
}
