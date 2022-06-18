package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    @BeforeAll
    static void beforeAllTests() {
        Configuration.baseUrl = "https://github.com/selenide";
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition = "0x0";
    }

    @AfterAll
    static void afterAllTest() throws InterruptedException {
        Configuration.holdBrowserOpen = true;
        Thread.sleep(2000);
        Configuration.holdBrowserOpen = false;
    }
}