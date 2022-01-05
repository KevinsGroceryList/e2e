package poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPOM {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(name = "username")
    WebElement usernameInput;

    @FindBy(name = "password")
    WebElement passwordInput;

    @FindBy(name = "firstname")
    WebElement firstnameInput;

    @FindBy(name = "lastname")
    WebElement lastnameInput;

    @FindBy(className = "btn-primary")
    WebElement registerBtn;

    @FindBy(className = "err-message")
    WebElement messageElem;

    public RegisterPOM(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));

        PageFactory.initElements(this.driver, this);
    }

    public void enterUsername(String username){
        this.usernameInput.sendKeys(username);
    }

    public void enterPassword(String password){
        this.passwordInput.sendKeys(password);
    }

    public void enterFirstname(String firstname){
        this.firstnameInput.sendKeys(firstname);
    }

    public void enterLastname(String lastname){
        this.lastnameInput.sendKeys(lastname);
    }

    public void submitRegister(){
        this.registerBtn.click();
    }

    public void waitForSuccessfulRegister(){
        this.wait.until(ExpectedConditions.urlToBe("http://localhost:4200/"));
    }

    public String getCurrentUrl(){
        return this.driver.getCurrentUrl();
    }

    public String getMessage(){
        this.wait.until(ExpectedConditions.visibilityOf(this.messageElem));
        return this.messageElem.getText();
    }


}
