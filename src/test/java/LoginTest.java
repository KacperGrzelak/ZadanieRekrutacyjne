import org.checkerframework.checker.units.qual.C;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    private WebDriver driver;
    private String password = PasswordUtils.getPassword(10);
    private String login = LoginUtils.getlogin(8);
    private String name = RestUtils.getName();
    private String lastname = RestUtils.getLastname();
    private String street = RestUtils.getStreet();
    private String postcode = RestUtils.getPostcode();
    private String place = RestUtils.getPlace(getPostcodeID());
    private String localNumber = RestUtils.getLocalNumber();
    private int province =  RestUtils.getProvince(getPostcodeID());
    private String phoneNumber = RestUtils.getPhoneNumber();
    private String email = RestUtils.getEmail(6);

    public int getPostcodeID(){
        int x = 0;
        for(int i = 0; i < RestUtils.POSTCODES.length; i++){
            String m = RestUtils.POSTCODES[i];
            if (m.equals(postcode)) {
             x = i;
             break;
            }
        }
        return x;
    }

    @Before
    public void setup() {
        String path = "C:\\Program Files\\ChromeDrivers\\100\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.nazwa.pl/panel/zaloguj-sie.html");

    }

    @Test
    public void registerTest()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("login"))));
        Assert.assertEquals(driver.getTitle(), "Panel Klienta - nazwa.pl");
        WebElement loginField = driver.findElement(By.id("login"));
        loginField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys(password);
        WebElement repeatPasswordField = driver.findElement(By.id("passRepeat"));
        repeatPasswordField.sendKeys(password);
        WebElement statusNotFirmCheckbox = driver.findElement(By.id("statusNotFirm"));
        statusNotFirmCheckbox.click();
        WebElement nameField = driver.findElement(By.id("firstName"));
        nameField.sendKeys(name);
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.sendKeys(lastname);
        WebElement streetField = driver.findElement(By.id("street"));
        streetField.sendKeys(street);
        WebElement localNoField = driver.findElement(By.id("localNo"));
        localNoField.sendKeys(localNumber);
        WebElement postCodeField = driver.findElement(By.id("postCode"));
        postCodeField.sendKeys(postcode);
        WebElement placeField = driver.findElement(By.id("place"));
        placeField.sendKeys(place);
        WebElement provinceSelector = driver.findElement(By.id("provinceId"));
        Select provinces = new Select(provinceSelector);
        provinces.selectByIndex(province+1);
        WebElement phoneField = driver.findElement(By.id("phone"));
        phoneField.sendKeys(phoneNumber);
        WebElement emailField = driver.findElement(By.id("client_Email"));
        emailField.sendKeys(email);
        WebElement acceptAllCheckbox = driver.findElement(By.id("acceptAll"));
        acceptAllCheckbox.click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name,'a-')]")));
        try{
        WebElement captcha = driver.findElement(By.xpath("//span[@id='recaptcha-anchor']"));
        captcha.click();}
        catch (Exception e){
            System.out.println("Captcha block");
        }
        driver.switchTo().defaultContent();
        wait.withTimeout(Duration.ofSeconds(5));
        WebElement registerButton = driver.findElement(By.id("_submit_PK_M0101"));
        registerButton.click();
        wait.withTimeout(Duration.ofSeconds(5));
        driver.findElement(By.linkText("Wyloguj")).click();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals("https://www.nazwa.pl/panel/Wylogowany.html", URL);
    }

    @Test
    public void loginTest()
    {
        WebElement loginField = driver.findElement(By.id("clientLogin"));
        loginField.sendKeys("3t-f8048");
        WebElement passwordField = driver.findElement(By.id("clientPass"));
        passwordField.sendKeys("%y:'PG8f;f");
        WebElement loginButton = driver.findElement(By.id("__submit_PK_M0118"));
        loginButton.click();
        String s = driver.getCurrentUrl();
        Assert.assertEquals("www.nazwa.pl/panel", s);
    }
    @Test
    public void wrongLoginTest()
    {
        WebElement loginField = driver.findElement(By.id("clientLogin"));
        loginField.sendKeys("3t-f8048");
        WebElement passwordField = driver.findElement(By.id("clientPass"));
        passwordField.sendKeys("y%y:'PG8f;f");
        WebElement loginButton = driver.findElement(By.id("__submit_PK_M0118"));
        loginButton.click();
        WebElement element = driver.findElement(By.linkText("Nieprawidłowy login lub hasło."));
        Assert.assertEquals("Nieprawidłowy login lub hasło.", element.getText());
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }


}
