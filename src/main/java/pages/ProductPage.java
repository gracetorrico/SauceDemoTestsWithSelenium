package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductPage
{
    WebDriver driver;

    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductName(String productName) {
        WebElement productNameElement = driver.findElement(By.className("inventory_details_name"));
        return productNameElement.getText();
    }

    // Get the product price on the product page
    public String getProductPrice(String productName) {
        WebElement productPriceElement = driver.findElement(By.className("inventory_details_price"));
        return productPriceElement.getText();
    }
}
