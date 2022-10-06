import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class TestCase {
    private static WebDriver driver;
    private static BatchInfo myTestBatch;
    private static EyesRunner testRunner;
    private static Configuration suiteConfig;
    Eyes eyes;

    @BeforeAll
    public static void beforeAll() {
        driver = WebDriverManager.chromedriver().create();
        myTestBatch = new BatchInfo("Test Cases");
        testRunner = new ClassicRunner();

        suiteConfig = new Configuration();
        suiteConfig.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        suiteConfig.setBatch(myTestBatch);
    }

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        eyes = new Eyes(testRunner);
        eyes.setConfiguration(suiteConfig);
        eyes.open(driver, "My First Tests", testInfo.getTestMethod().get().getName(), new RectangleSize(1000, 600));
    }

    @Test
    public void applitoolsHelloWorld() {
        driver.get("https://applitools.com/helloworld/");
        eyes.check(Target.window());
    }

    @Test
    public void example() {
        driver.get("https://example.com");
        eyes.check(Target.window());
    }

    @AfterEach
    public void afterEach() {
        eyes.closeAsync();
    }

    @AfterAll
    public static void afterAll() {
        driver.close();
        TestResultsSummary results = testRunner.getAllTestResults();
        System.out.println(results);
    }

}
