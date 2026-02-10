package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createButton_IsDisplayed(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/list");
        WebElement createButton = driver.findElement(By.xpath("//a[contains(text(),'Create Product')]"));
        assertTrue(createButton.isDisplayed());
    }

    @Test
    void createFormPage_IsDisplayed(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void createProduct_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        WebElement input_productName = driver.findElement(By.id("nameInput"));
        WebElement input_productQuantity = driver.findElement(By.id("quantityInput"));

        input_productName.sendKeys("Sampo Cap Bambang");
        input_productQuantity.sendKeys("10");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement Row1Column1 = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
        WebElement Row1Column2 = driver.findElement(By.xpath("//tbody/tr[1]/td[2]"));

        assertEquals("Sampo Cap Bambang", Row1Column1.getText());
        assertEquals("10", Row1Column2.getText());
    }

    @Test
    void createMultipleProduct_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        // Create first product
        driver.findElement(By.id("nameInput")).sendKeys("Sampo Cap Bambang");
        driver.findElement(By.id("quantityInput")).sendKeys("10");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.get(baseUrl + "/product/create");

        // Create second product
        driver.findElement(By.id("nameInput")).sendKeys("Buku");
        driver.findElement(By.id("quantityInput")).sendKeys("101");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement Row2Column1 = driver.findElement(By.xpath("//tbody/tr[2]/td[1]"));
        WebElement Row2Column2 = driver.findElement(By.xpath("//tbody/tr[2]/td[2]"));

        assertEquals("Buku", Row2Column1.getText());
        assertEquals("101", Row2Column2.getText());
    }
}