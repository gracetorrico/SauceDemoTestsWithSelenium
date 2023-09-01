package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverManager;

import java.util.List;

public class YourCartPage {
    WebDriver driver;
    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    List<WebElement> productPrices;

    @FindBy(className = "cart_item")
    List<WebElement> cartItems;
    @FindBy(className ="shopping_cart_badge")
    WebElement shoppingCartBadge;

    @FindBy(xpath = "//*[@id=\"checkout\"]")
    WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    WebElement continueShopping;

    public YourCartPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isProductDisplayed(String product)
    {
        for (WebElement element: productNames)
        {
            if (element.getText().equalsIgnoreCase(product))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isPriceDisplayed(String price)
    {
        for (WebElement element: productPrices)
        {
            if (element.getText().equalsIgnoreCase(price))
            {
                return true;
            }
        }
        return false;
    }

    public double totalPrice()
    {
        double total = 0.0;
        for (WebElement element: productPrices)
        {
            total=total + Double.parseDouble(element.getText().substring(1));
        }
        return total;
    }

    public boolean isProductPriceRight(String product, String price) {
        for (WebElement element : cartItems) {
            WebElement productNameElement = element.findElement(By.className("inventory_item_name"));
            WebElement productPriceElement = element.findElement(By.className("inventory_item_price"));

            String productName = productNameElement.getText();
            String productPrice = productPriceElement.getText();

            if (productName.equals(product)) {
                return productPrice.equals(price);
            }
        }
        return false;
    }


    public boolean shoppingCartBadgeIsDisplayed()
    {
        List<WebElement> shopping_cart_badge = driver.findElements(By.className("shopping_cart_badge"));
        return !shopping_cart_badge.isEmpty();
    }

    public void removeProduct(String productName)
    {
        String productNameLowerCase = productName.toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String removeProductId = "remove-";
        removeProductId = removeProductId + productNameLowerCase;
        WebElement removeButton = driver.findElement(By.id(removeProductId));

        removeButton.click();
    }

    public String getNumberOnShoppingCartBadgeButton(){
        return shoppingCartBadge.getText();
    }
    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }
    public void clickOnContinueShoppingButton(){
        continueShopping.click();
    }
}
