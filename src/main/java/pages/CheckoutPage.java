package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;
    @FindBy(xpath = "//*[@id=\"first-name\"]")
    WebElement firstNameTextBox;
    @FindBy(xpath = "//*[@id=\"last-name\"]")
    WebElement lastNameTextBox;
    @FindBy(xpath = "//*[@id=\"postal-code\"]")
    WebElement postalCodeTextBox;
    @FindBy(xpath = "//*[@id=\"continue\"]")
    WebElement continueButton;

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void setFirstNameTextBox(String userName){
        firstNameTextBox.sendKeys(userName);
    }
    public void setLastNameTextBox(String lastName){
        lastNameTextBox.sendKeys(lastName);
    }
    public void setPostalCodeTextBox(String postalCode){
        postalCodeTextBox.sendKeys(postalCode);
    }

    public void clickOnContinueButton(){
        continueButton.click();
    }

}
