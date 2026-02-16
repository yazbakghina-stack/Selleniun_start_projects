package Selleniun_start_projects.Selleniun_start_projects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTests {

    // בפרויקט זה מותקנת גרסת JUnit 5 (org.junit.jupiter.api),
    // לכן נעשה שימוש ב־@BeforeEach ו־@AfterEach

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://testpages.eviltester.com/apps/button-calculator/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void click(String id) {
        driver.findElement(By.id(id)).click();
    }

    private String getResult() {
        return driver.findElement(By.id("calculated-display")).getAttribute("value");
    }

    // ==================== ADDITION (+) ====================

    @Test
    public void add_case1_regular_shouldReturn10() {
        // 4 + 6 = 10
        click("button04");
        click("buttonplus");
        click("button06");
        click("buttonequals");

        assertEquals("10", getResult());
    }

    @Test
    public void add_case2_withZero_shouldReturn9() {
        // 0 + 9 = 9
        click("button00");
        click("buttonplus");
        click("button09");
        click("buttonequals");

        assertEquals("9", getResult());
    }

    @Test
    public void add_case3_smallNumbers_shouldReturn8() {
        // 7 + 1 = 8
        click("button07");
        click("buttonplus");
        click("button01");
        click("buttonequals");

        assertEquals("8", getResult());
    }

    // ==================== SUBTRACTION (-) ====================

    @Test
    public void sub_case1_positiveResult_shouldReturn5() {
        // 9 - 4 = 5
        click("button09");
        click("buttonminus");
        click("button04");
        click("buttonequals");

        assertEquals("5", getResult());
    }

    @Test
    public void sub_case2_negativeResult_shouldReturnMinus5() {
        // 3 - 8 = -5
        click("button03");
        click("buttonminus");
        click("button08");
        click("buttonequals");

        assertEquals("-5", getResult());
    }

    @Test
    public void sub_case3_withZero_shouldReturn6() {
        // 6 - 0 = 6
        click("button06");
        click("buttonminus");
        click("button00");
        click("buttonequals");

        assertEquals("6", getResult());
    }

    // ==================== MULTIPLICATION (x) ====================

    @Test
    public void mul_case1_regular_shouldReturn10() {
        // 2 x 5 = 10
        click("button02");
        click("buttonmultiply");
        click("button05");
        click("buttonequals");

        assertEquals("10", getResult());
    }

    @Test
    public void mul_case2_withZero_shouldReturn0() {
        // 0 x 8 = 0
        click("button00");
        click("buttonmultiply");
        click("button08");
        click("buttonequals");

        assertEquals("0", getResult());
    }

    @Test
    public void mul_case3_withOne_shouldReturn9() {
        // 9 x 1 = 9
        click("button09");
        click("buttonmultiply");
        click("button01");
        click("buttonequals");

        assertEquals("9", getResult());
    }
}
