package selenium.tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.PRODUCT_MEN_DENIM_SHIRT_PATH;

public class ItemSearchTests extends BaseTestSetup {

    @BeforeEach
    public void navigateToPageCategoriesPage() {
        categoriesPage.navigateToPage();
    }

    @Test
    @Description("SDP-59 [Search] Verify search functionality as a guest")
    public void verifyGuestSearchFunctionality() {
        categoriesPage.assertPageNavigated();
        categoriesPage.performSearch();
        categoriesPage.assertSearchedProductIsPresent(PRODUCT_MEN_DENIM_SHIRT_PATH);
        categoriesPage.assertElementTextEquals(PRODUCT_MEN_DENIM_SHIRT_PATH, "Denim Shirt");
    }
}
