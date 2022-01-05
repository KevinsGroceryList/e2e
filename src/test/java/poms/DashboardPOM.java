package poms;

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

public class DashboardPOM {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(name = "item-name")
    WebElement itemInput;

    @FindBy(className = "btn-primary")
    WebElement createItemBtn;

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

    private String getItemName(WebElement item){
        WebElement nameElem = item.findElement(By.className("item-name"));
        return nameElem.getText();
    }
}
