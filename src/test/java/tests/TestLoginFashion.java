package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;


public class TestLoginFashion {
    private WebDriver driver;
    private WebDriverWait wait;
    private String email;
    private String password;

    @BeforeTest
    public void setupSelenium() throws IOException   {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        prop.load(inputStream);

        String chromeDriverURL = prop.getProperty("CHROMEDRIVER_PATH");
        email = prop.getProperty("usuario");
        password = prop.getProperty("password");

        System.setProperty("webdriver.chrome.driver", chromeDriverURL);
        driver = new ChromeDriver();// Inicializar el WebDriver
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));



    }

    @Test
    private  void login() {
        String baseURL = "https://fashionspark.com/";
        driver.get(baseURL);

        WebElement botonUsuario = waitForElementToBeClickable(By.xpath("//*[@id=\"boton-usr\"]"));
        botonUsuario.click();
        WebElement enlace = waitForElementToBeClickable(By.xpath("//*[@id=\"links\"]/div[1]/a"));
        enlace.click();

        WebElement emailInput = waitForElementToBeClickable(By.xpath("//*[@id=\"email\"]"));
        emailInput.sendKeys(email);

        WebElement passwordInput = waitForElementToBeClickable(By.xpath("//*[@id=\"pass\"]"));
        passwordInput.sendKeys((password));

        WebElement loginButon = waitForElementToBeClickable(By.xpath("//*[@id=\"send2\"]/span"));
        loginButon.click();

    }


    @Test
    void verificarCategoriaHombre() {
        WebElement webElement = waitForElementToBeClickable(By.xpath("//*[@id=\"mainMenu\"]/li[3]/div/span[1]/a"));
        String actualCategory = webElement.getText();
        String expectedCategory = "Hombre";
        Assert.assertEquals(expectedCategory, actualCategory);
    }

    @Test(dependsOnMethods = "verificarCategoriaHombre")
    void cierreSesion() {
        WebElement botonUsuario = waitForElementToBeClickable(By.id("boton-usr"));
        botonUsuario.click();
        WebElement linkCierre = waitForElementToBeClickable(By.xpath("//*[@id=\"links\"]/div[2]/a"));
        linkCierre.click();
    }

    @AfterTest
    public void browserClose() {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
