package hooksinitializations;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class hookssteps {

    @Before
    public void before() {
        System.out.println("before fearture");
    }

    @After
    public void after() {
        System.out.println("after fearture");
    }

    @BeforeStep
    public static void beforeal() {
        System.out.println("before step");
    }


}
