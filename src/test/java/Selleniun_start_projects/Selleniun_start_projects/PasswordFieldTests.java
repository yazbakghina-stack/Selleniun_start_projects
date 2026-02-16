package Selleniun_start_projects.Selleniun_start_projects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordFieldTests {

    /*
     * בדיקות לפי Equivalence Partitioning (EP)
     *
     * תנאי התקינות כפי שנצפה במערכת:
     * 1) אורך הקלט חייב להיות לפחות 7 תווים (כלומר: <7 לא תקין, >=7 תקין)
     * 2) כל תו חייב להיות אחד מהבאים בלבד: A-Z, a-z, 0-9, *
     *
     * מחלקות שקילות שקבענו:
     * EP תקין:
     *  - אורך >= 7 וכל התווים מותרים  -> "Valid Value"
     *
     * EP לא תקין:
     *  - אורך < 7 (ורק זה נשבר)       -> "Invalid Value"
     *  - קיים תו לא חוקי (והאורך >=7) -> "Invalid Value"
     */

    private WebDriver driver;

    // בפרויקט זה מותקנת גרסת JUnit 5 (org.junit.jupiter.api).
    // לכן השתמשתי ב־@BeforeEach ו־@AfterEach, שהם המקבילים ל־@Before ו־@After (JUnit 4).
    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://testpages.eviltester.com/apps/7-char-val/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void submit(String value) {
        // שדה הקלט בדף מזוהה לפי name="characters"
        driver.findElement(By.name("characters")).clear();
        driver.findElement(By.name("characters")).sendKeys(value);

        // כפתור הבדיקה מזוהה לפי name="validate"
        driver.findElement(By.name("validate")).click();
    }

    private String getValidationMessage() {
        // ההודעה מוצגת בשדה name="validation_message"
        return driver.findElement(By.name("validation_message"))
                .getAttribute("value");
    }

    // -------------------- EP: קלט תקין --------------------

    @Test
    public void EP_valid_shouldReturnValidValue() {
        // קלט תקין: אורך >= 7 וכל התווים מותרים
        submit("Ab1c*G5");

        String msg = getValidationMessage();
        assertEquals("Valid Value", msg);
    }

    // -------------------- EP: קלט לא תקין (כל פעם תנאי אחד נשבר) --------------------

    @Test
    public void EP_invalid_lengthLessThan7_shouldReturnInvalidValue() {
        // קלט לא תקין: אורך < 7 (כאן נשבר רק תנאי האורך)
        submit("Ab2c*M"); // 6 תווים

        String msg = getValidationMessage();
        assertEquals("Invalid Value", msg);
    }

    @Test
    public void EP_invalid_containsIllegalCharacter_shouldReturnInvalidValue() {
        // קלט לא תקין: תו אסור (כאן נשבר רק תנאי התווים, והאורך נשאר >=7)
        submit("As6c@D1"); // @ תו לא חוקי

        String msg = getValidationMessage();
        assertEquals("Invalid Value", msg);
    }
}
