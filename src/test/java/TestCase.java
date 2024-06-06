import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;

public class TestCase {
    WebDriver driver;
    Eyes eyes;

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        eyes = new Eyes();
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        driver = WebDriverManager.chromedriver().create();
        eyes.open(driver, "My First Tests", testInfo.getTestMethod().get().getName(), new RectangleSize(1000, 600));
    }

    @Test
    public void myTestCase() {
        driver.get("https://applitools.com/helloworld/");
        eyes.check(Target.window());
    }

    @AfterEach
    public void afterEach() {
        eyes.closeAsync();;
        driver.close();
    }
}
