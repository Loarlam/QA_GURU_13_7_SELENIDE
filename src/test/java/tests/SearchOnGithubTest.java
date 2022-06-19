package tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchOnGithubTest extends BaseTest {
    private SelenideElement wiki = $("#wiki-tab"),
            searchLine = $("input.js-filterable-field"),
            searchResult = $("[data-filterable-type=\"substring\"]"),
            markdownBody = $(".markdown-body");
    private ElementsCollection searchTag = $$("h4");
    private String searchInWikiSearchLine = "Soft",
            searchInWikiSearchResults = "SoftAssertions",
            searchText = "JUnit5",
            searchCode = "@ExtendWith({SoftAssertsExtension.class})";

    @Test
    @DisplayName("Страница SoftAssertions содержит заголовок и код с JUnit5")
    void searchingForExampleCodeJUnit() {
        open("/selenide");
        wiki.click();
        searchLine.setValue(searchInWikiSearchLine).pressEnter();
        searchResult.shouldHave(exactText(searchInWikiSearchResults)).click();
        searchTag.filterBy(text(searchText)).shouldHave(size(1));
        markdownBody.shouldHave(text(searchCode));
    }
}
