package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span")
    WebElement checkoutTitle;

    @FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]")
    WebElement subtotal;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(className = "complete-header")
    WebElement completeHeaderLabel;

    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isCheckoutTitleDisplayed()
    {
        return checkoutTitle.isDisplayed();
    }

    public double getSubTotal()
    {
        return Double.parseDouble(subtotal.getText().substring(13));
    }
    public void clickOnFinishButton()
    {
        finishButton.click();
    }

    public boolean isCompleteLabelDisplayed()
    {
        return  completeHeaderLabel.isDisplayed();
    }
}
