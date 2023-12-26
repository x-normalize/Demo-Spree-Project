package selenium.tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class AddItemsToCartTest extends BaseTestSetup {

    @BeforeEach
    public void loginAndNavigateToPage() {
        loginPage.navigateToPage();
        loginPage.login(LANDON_BUTLER_USERNAME, LANDON_BUTLER_PASSWORD);
    }

    @AfterEach
    public void logout() {
        accountPage.logout();
        loginPage.navigateToHomePage();
    }

    @Test
    @Description("SDP-37 [Shopping Cart] Add items to the shopping cart from men's category")
    public void shouldAddItemsToCartFromMensCategory() {
        menCategoriesPage.navigateToPage();
        menCategoriesPage.assertPageNavigated();
        menCategoriesPage.assertElementPresent(PRODUCT_MEN_DENIM_SHIRT_PATH);
        menCategoriesPage.addItemsToCartFromMensCategory();
        menCategoriesPage.assertSuccessfulAddToCardMessageIsPresent();
        menCategoriesPage.assertElementTextEquals(PRODUCT_PRICE_PATH, "$60.99");
        menCategoriesPage.assertItemQuantityInCart("Denim Shirt", 1);
        menCategoriesPage.assertItemPresentInCart("Denim Shirt");
        menCategoriesPage.assertTotalPrice("$60.99");
        menCategoriesPage.deleteItemFromCard();
        menCategoriesPage.assertSuccessfulDeleteItemMessageIsPresent();

    }

}
