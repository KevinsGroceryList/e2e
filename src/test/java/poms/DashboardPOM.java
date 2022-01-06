package poms;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DashboardPOM {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(name = "item-name")
    WebElement itemInput;

    @FindBy(className = "btn-primary")
    WebElement createItemBtn;

    @FindBy(id="checkout-btn")
    WebElement checkoutBtn;

    @FindBy(className = "grocery-item")
    List<WebElement> items = new ArrayList<>();

    public DashboardPOM(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));

        PageFactory.initElements(this.driver, this);
    }

    public void enterItem(String item){
        this.itemInput.sendKeys(item);
    }

    public void submitItem(){
        this.createItemBtn.click();
    }

    public void waitForListToAddItem(){
        this.items = this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("grocery-item"), this.items.size()));
    }

    public void waitForListToRemoveItem(){
        this.items = this.wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className("grocery-item"), this.items.size()));
    }

    public Boolean isItemPresent(String itemName){
        for(WebElement item : this.items){
            if(getItemName(item).equals(itemName)) {
                return true;
            }
        }

        return false;
    }

    public Boolean clickItemGivenName(String itemName){
        WebElement item = this.getItemGivenName(itemName);

        if(item == null) {
            return false;
        }

        item.click();

        /*
        * What is xpath?
        *   xpath is a query language for markup languages (xml / html)
        *   - you can traverse an element like a file structure.
        * */

        // the below line did not work
        //this.wait.until(ExpectedConditions.attributeContains(this.driver.findElement(By.xpath("//*/app-dashboard/div/div[1]/h3")),"class", "item-name toggle"));
        this.wait.until(ExpectedConditions.invisibilityOf(item.findElement(By.className("item-name"))));
        return true;
    }

    public Boolean isItemInCart(String itemName){
        WebElement item = this.getItemGivenName(itemName);

        if(item == null)
            return false;

        return item.findElement(By.className("toggle")).getAttribute("class").contains("toggle");
    }

    public Boolean clickDeleteBtn(String itemName){
        WebElement item = this.getItemGivenName(itemName);

        if(item == null)
            return false;

        WebElement deleteBtn = item.findElement(By.className("btn-danger"));

        deleteBtn.click();
        return true;
    }

    public void clickCheckOutBtn(){
        this.checkoutBtn.click();
    }

    public Boolean isCheckoutBtnVisible(){
        this.wait.until(ExpectedConditions.invisibilityOf(checkoutBtn));
        return false;
    }


    private WebElement getItemGivenName(String itemName){
        for(WebElement item : this.items){
            if(getItemName(item).equals(itemName)) {
                return item;
            }
        }

        return null;
    }

    private String getItemName(WebElement item){
        WebElement nameElem = item.findElement(By.className("item-name"));
        return nameElem.getText().toLowerCase();
    }
}
