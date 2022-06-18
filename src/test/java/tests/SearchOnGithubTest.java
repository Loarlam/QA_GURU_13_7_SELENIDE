package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchOnGithubTest extends BaseTest {
    @CsvSource(value = "JUnit5, @ExtendWith({SoftAssertsExtension.class})")
    @ParameterizedTest(name = "На странице SoftAssertions присутствуют {0} и его код {1}...")
    void searchingForExampleCodeJUnit(String searchText, String searchCode) {
        open("/selenide");
        $("#wiki-tab").click();
        $("input.js-filterable-field").setValue("Soft").pressEnter();
        $("[data-filterable-type=\"substring\"]").shouldHave(exactText("SoftAssertions")).click();
        $$("h4").filterBy(text(searchText)).shouldHave(size(1));
        $(".markdown-body").shouldHave(exactText(searchCode));
    }
}
