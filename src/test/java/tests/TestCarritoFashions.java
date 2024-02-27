package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;


public class TestCarritoFashions {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setupSelenium() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        prop.load(inputStream);

        String chromeDriverURL = prop.getProperty("CHROMEDRIVER_PATH");


        System.setProperty("webdriver.chrome.driver", chromeDriverURL);
        driver = new ChromeDriver();// Inicializar el WebDriver
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));

    }


    @Test
    private void AgregarDosProductosAlCarrito() {
        String baseURL = "https://fashionspark.com/";
        driver.get(baseURL);

        WebElement linkMujer = waitForElementToBeClickable(By.xpath("//*[@id=\"mainMenu\"]/li[2]/div/span[1]/a"));
        // Crear un objeto Actions
        Actions actions = new Actions(driver);
        // Mover el mouse sobre el elemento "Mujer"
        actions.moveToElement(linkMujer).perform();

        WebElement linkJeans = waitForElementToBeClickable(By.xpath("//*[@id=\"mainMenu\"]/li[2]/ul/li/div/div[1]/ul/li[2]/span/a"));
        linkJeans.click();
        WebElement elegirTalla = waitForElementToBeClickable(By.xpath("//*[@id=\"narrow-by-list\"]/dd[2]/div/div/a[6]/div"));
        elegirTalla.click();
        WebElement elegirColor = waitForElementToBeClickable(By.xpath("//*[@id=\"narrow-by-list\"]/dd[2]/div/div/a[1]/div"));
        elegirColor.click();
        WebElement elegirJeans = waitForElementToBeClickable(By.xpath("//*[@id=\"product-item-info_385203\"]/div[2]/strong/a"));
        elegirJeans.click();
        WebElement elegitTallaDeNuevo = waitForElementToBeClickable(By.xpath("//*[@id=\"option-label-talla-596-item-761\"]"));
        elegitTallaDeNuevo.click();
        WebElement agregarAlCarrito = waitForElementToBeClickable(By.xpath("//*[@id=\"product-addtocart-button\"]/span"));
        agregarAlCarrito.click();

        // busqueda de segundo Producto
        WebElement Inicio = waitForElementToBeClickable(By.xpath("//*[@id=\"html-body\"]/div[5]/div[2]/ul/li[1]/a"));
        Inicio.click();
        WebElement linkMujerDos = waitForElementToBeClickable(By.xpath("//*[@id=\"mainMenu\"]/li[2]/div/span[1]/a"));
        Actions actions2 = new Actions(driver);
        actions2.moveToElement(linkMujerDos).perform();

        WebElement linkPoleras = waitForElementToBeClickable(By.xpath("//*[@id=\"mainMenu\"]/li[2]/ul/li/div/div[1]/ul/li[3]/span/a"));
        linkPoleras.click();
        WebElement elegirTallaM = waitForElementToBeClickable(By.xpath("//*[@id=\"narrow-by-list\"]/dd[2]/div/div/a[3]/div"));
        elegirTallaM.click();
        WebElement elegirSegundoProducto = waitForElementToBeClickable(By.xpath("//*[@id=\"product-item-info_425129\"]/div[2]/strong/a"));
        elegirSegundoProducto.click();
        WebElement elegirDeNuevoTallaM = waitForElementToBeClickable(By.xpath("//*[@id=\"option-label-talla-596-item-827\"]"));
        elegirDeNuevoTallaM.click();
        WebElement agregarAlCarritoSegundoProducto = waitForElementToBeClickable(By.xpath("//*[@id=\"product-addtocart-button\"]/span"));
        agregarAlCarritoSegundoProducto.click();
        WebElement irAlCarro = waitForElementToBeClickable(By.xpath("//*[@id=\"html-body\"]/div[5]/header/div[2]/div[4]/div[3]/a/span[2]/span[1]"));
        irAlCarro.click();
        WebElement comprarAhora = waitForElementToBeClickable(By.xpath("//*[@id=\"top-cart-btn-checkout\"]"));
        comprarAhora.click();
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
