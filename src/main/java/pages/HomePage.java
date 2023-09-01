package pages;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomePage {
    WebDriver driver;

    @FindBy(className = "app_logo")
    WebElement pageTitle;

    @FindBy(className = "product_sort_container")
    WebElement sortComboBox;

    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartButton;

    @FindBy(id = "react-burger-menu-btn")
    WebElement hamburgerButton;

    @FindBy(className ="shopping_cart_badge")
    WebElement shoppingCartBadge;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageTitleIsDisplayed(){
        return pageTitle.isDisplayed();
    }

    public void selectSortComboBox(String option){
        Select selectObject = new Select(sortComboBox);
        selectObject.selectByVisibleText(option);
    }

    public boolean areProductsInDescendantOrderByName(){
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProductNames = new ArrayList<>();

        for( WebElement element: products){
            actualProductNames.add(element.getText());
        }

        return Ordering.natural().reverse().isOrdered(actualProductNames);

    }

    public boolean areProductsInAscendantOrderByPrice(){
        List<WebElement> products = driver.findElements(By.className("inventory_item_price"));
        List<Double> actualProductOrder = new ArrayList<>();

        for (WebElement element : products) {
            String priceText = element.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            actualProductOrder.add(price);
        }

        return Ordering.natural().isOrdered(actualProductOrder);

    }

    public void addProductToCart(String productName)
    {
        String productNameLowerCase = productName.toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String addToCartId = "add-to-cart-";
        addToCartId = addToCartId + productNameLowerCase;
        WebElement addToCartButton = driver.findElement(By.id(addToCartId));

        addToCartButton.click();
    }

    public void clickOnShoppingCartButton(){
        shoppingCartButton.click();
    }
    public void clickOnHamburgerButton(){
        hamburgerButton.click();
    }

    public void clickOnLogoutLink()
    {
        WebElement logoutLink = new WebDriverWait(DriverManager.getDriver().driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));

        logoutLink.click();
    }

    public void clickOnResetAppState()
    {
        WebElement logoutLink = new WebDriverWait(DriverManager.getDriver().driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link")));

        logoutLink.click();
    }

    public String getNumberOnShoppingCartBadgeButton(){
        return shoppingCartBadge.getText();
    }

    public boolean isShoppingCartBadgeIsDisplayed()
    {
        List<WebElement> shopping_cart_badge = driver.findElements(By.className("shopping_cart_badge"));
        return !shopping_cart_badge.isEmpty();
    }

    public boolean isRemovedButtonDisplayed(String productName)
    {
        String productNameLowerCase = productName.toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String removeProductId = "remove-";
        removeProductId = removeProductId + productNameLowerCase;
        WebElement removeButton = driver.findElement(By.id(removeProductId));

        return removeButton.isDisplayed();
    }

    public void clickOnSocial(String social)
    {
        WebElement socialButton = driver.findElement(By.className("social_" + social.toLowerCase()));
        socialButton.click();
    }

    public String getExpectedURL(String socialNetwork) {
        return switch (socialNetwork) {
            case "linkedin" -> "https://www.linkedin.com/company/sauce-labs/";
            case "twitter" -> "https://twitter.com/saucelabs";
            case "facebook" -> "https://www.facebook.com/saucelabs";
            default -> throw new IllegalArgumentException("Red social no válida: " + socialNetwork);
        };
    }

    public void clickOnProduct(String productName) {
        // Find all product elements on the page
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));

        // Iterate through the product elements to find the matching product name
        for (WebElement productElement : productElements) {
            if (productElement.getText().equals(productName)) {
                // Click on the matching product element
                productElement.click();
                break;
            }
        }
    }

    // Get the product name for a given product
    public String getProductName(String productName) {
        // Find all product elements on the page
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));

        // Iterate through the product elements to find the matching product name
        for (WebElement productElement : productElements) {
            if (productElement.getText().equals(productName)) {
                // Return the matching product name
                return productElement.getText();
            }
        }

        // If the product is not found, return null or an appropriate value
        return null;
    }

    // Get the product price for a given product
    public String getProductPrice(String productName) {
        // Find all price elements on the page
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));

        // Iterate through the price elements to find the matching product name and its corresponding price
        for (int i = 0; i < priceElements.size(); i++) {
            WebElement productElement = driver.findElements(By.className("inventory_item_name")).get(i);
            if (productElement.getText().equals(productName)) {
                // Return the price of the matching product
                return priceElements.get(i).getText();
            }
        }

        // If the product is not found, return null or an appropriate value
        return null;
    }
}
