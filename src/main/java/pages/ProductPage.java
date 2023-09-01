package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage
{
    WebDriver driver;
    @FindBy (className = "inventory_details_name")
    WebElement productNameElement;

    @FindBy (className = "inventory_details_price")
    WebElement productPriceElement;

    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductName(String productName) {
        return productNameElement.getText();
    }

    public String getProductPrice(String productName) {
        return productPriceElement.getText();
    }
}
