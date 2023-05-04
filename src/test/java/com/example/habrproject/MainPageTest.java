package com.example.habrproject;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;


    @BeforeAll
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");


    }
    @AfterAll
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void languageChange() {


        WebElement profile = driver.findElement(By.cssSelector("[data-test-id=\"menu-toggle-guest\"]"));
        profile.click();

        WebElement langButton = driver.findElement(By.cssSelector("svg[class=\"tm-svg-img tm-user-menu__item-icon\"]"));
        langButton.click();

        WebElement checkBoks = driver.findElement(By.xpath("//*[contains(text(), \"English\")]"));
        checkBoks.click();

        WebElement saveChange = driver.findElement(By.cssSelector("[type=\"submit\"]"));
        saveChange.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Posts')]")).isDisplayed(), "Posts don't found");
    }

    @Test
    public void sortCompanyName() {

        WebElement company = driver.findElement(By.xpath("//*[contains(text(), 'Компании')]"));
        company.click();

        WebElement nameUP = driver.findElement(By.xpath("//*[contains(text(), 'Название')]"));
        nameUP.click();

        WebElement nameSort = driver.findElement(By.xpath("//*[contains(text(), 'Название')]"));
        nameSort.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Acer')]")).isDisplayed(), "Результат не найден");

    }

}
