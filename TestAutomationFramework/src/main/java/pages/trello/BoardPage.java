package pages.trello;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BoardPage extends BaseTrelloPage {


    public BoardPage(WebDriver driver) {
        super(driver, "trello.boardPage");
    }

    public void addCardToList() {
        String nameCard = getUIMappingByKey("trello.cardName");
        String textArea = getUIMappingByKey("trello.boardsPage.cardTextAreaField");

        actions.waitForElementClickable("trello.boardsPage.myBoardsButton");
        actions.clickElement("trello.boardsPage.myBoardsButton");
        actions.waitForElementClickable("trello.boardsPage.addCardTitle");
        actions.clickElement("trello.boardsPage.addCardTitle");
        actions.waitForElementClickable("trello.boardsPage.cardTextAreaField");
        actions.clickElement("trello.boardsPage.cardTextAreaField");

        actions.waitForElementVisible("trello.boardsPage.cardTextAreaField");
        actions.typeValueInField(nameCard, "trello.boardsPage.cardTextAreaField");
        actions.waitForElementClickable("trello.boardsPage.addCardButton");
        actions.clickElement("trello.boardsPage.addCardButton");
    }

    public void moveCardToList(String cardName, String listName) {
    }

    public void assertListExists(String listName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName);
    }

    public void assertCardExists(String cardName) {
        actions.waitForElementPresent("trello.boardPage.cardByName", cardName);
    }
}
