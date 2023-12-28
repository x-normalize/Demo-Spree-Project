package selenium.tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class ItemSearchTests extends BaseTestSetup {
    @Test
    @Description("SDP-59 [Search] Verify search functionality as a guest")
    public void verifyGuestSearchFunctionality() {
        categoriesPage.navigateToPage();
        categoriesPage.assertPageNavigated();
        categoriesPage.performSearchDenimShirt();
        categoriesPage.assertSearchedProductIsPresent(PRODUCT_MEN_DENIM_SHIRT_PATH);
        categoriesPage.assertElementTextEquals(PRODUCT_MEN_DENIM_SHIRT_PATH, "Denim Shirt");
    }

    @Test
    @Description("SDP-60 [Search] Verify search functionality as an authenticated user")
    public void verifyAuthenticatedUserSearchFunctionality() {
        loginPage.navigateToPage();
        loginPage.assertPageNavigated();
        loginPage.login(HEIDI_DIXON_USERNAME, HEIDI_DIXON_PASSWORD);
        categoriesPage.navigateToPage();
        categoriesPage.assertPageNavigated();
        categoriesPage.performSearchCoveredPlacketShirt();
        categoriesPage.assertSearchedProductIsPresent(PRODUCT_PLACKET_SHIRT);
        categoriesPage.assertElementTextEquals(PRODUCT_PLACKET_SHIRT, "Covered Placket Shirt");
        accountPage.logout();
    }


}
