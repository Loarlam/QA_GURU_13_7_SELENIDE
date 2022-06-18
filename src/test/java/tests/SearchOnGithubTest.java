package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchOnGithubTest extends BaseTest {
    private SelenideElement wiki = $("#wiki-tab"),
                            searchLine = $("input.js-filterable-field"),
                            searchResult = $("[data-filterable-type=\"substring\"]"),
                            markdownBody = $(".markdown-body");
    private ElementsCollection searchTag = $$("h4");

    @CsvSource(value = "Soft, SoftAssertions, JUnit5, @ExtendWith({SoftAssertsExtension.class})")
    @ParameterizedTest(name = "На странице SoftAssertions присутствуют {2} и его код {3}...")
    void searchingForExampleCodeJUnit(String searchInWikiSearchLine, String searchInWikiSearchResults, String searchText, String searchCode) {
        open("/selenide");
        wiki.click();
        searchLine.setValue(searchInWikiSearchLine).pressEnter();
        searchResult.shouldHave(exactText(searchInWikiSearchResults)).click();
        searchTag.filterBy(text(searchText)).shouldHave(size(1));
        markdownBody.shouldHave(text(searchCode));
    }
}
