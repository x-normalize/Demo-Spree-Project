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
        menCategoriesPage.addItemsToCartFromMensCategory();
        menCategoriesPage.assertSuccessfulAddToCardMessageIsPresent();
        menCategoriesPage.assertDenimShirtPrice(PRODUCT_PRICE_PATH, "$60.99");
        actions.assertItemQuantityInCart("Denim Shirt", 1);
        actions.assertItemPresentInCart("Denim Shirt");
        actions.assertTotalPrice("$60.99");
        menCategoriesPage.deleteItemFromCard();
        menCategoriesPage.assertSuccessfulDeleteItemMessageIsPresent();
    }

    @Test
    @Description("SDP-38 [Shopping Cart] Add items to the shopping cart from men's category with blue color")
    public void shouldAddBlueItemsFromMensCategoryToCart() {
        menCategoriesPage.navigateToPage();
        menCategoriesPage.assertPageNavigated();
        menCategoriesPage.addBlueItemsToCartFromMensCategory();
        menCategoriesPage.assertSuccessfulAddToCardMessageIsPresent();
        menCategoriesPage.assertBlueColorShirtPrice(BLUE_COLOR_PRODUCT_PRICE_PATH, "$41.99");
        actions.assertItemQuantityInCart("Covered Placket Shirt", 1);
        actions.assertItemPresentInCart("Covered Placket Shirt");
        actions.assertTotalPrice("$41.99");
        menCategoriesPage.deleteItemFromCard();
        menCategoriesPage.assertSuccessfulDeleteItemMessageIsPresent();
    }

    @Test
    @Description("SDP-39 [Shopping Cart] Add Items to the shopping cart from men's category with size L")
    public void shouldAddLSizesItemsFromMensCategoryToCart() {
        menCategoriesPage.navigateToPage();
        menCategoriesPage.assertPageNavigated();
        menCategoriesPage.addLSizeItemToCardFromMensCategory();
        menCategoriesPage.assertSuccessfulAddToCardMessageIsPresent();
        menCategoriesPage.assertProductAnorakIsPresent();
        actions.assertItemQuantityInCart("Anorak With Hood", 1);
        actions.assertItemPresentInCart("Anorak With Hood");
        actions.assertTotalPrice("$37.99");
        menCategoriesPage.deleteItemFromCard();
        menCategoriesPage.assertSuccessfulDeleteItemMessageIsPresent();
    }

    @Test
    @Description("SDP-40 [Shopping Cart] Add items to the shopping cart from men's category with price less than " +
            "$50 USD")
    public void shouldAddItemsFromMensCategoryToCartWithPriceLessThan50USD() {
        menCategoriesPage.navigateToPage();
        menCategoriesPage.assertPageNavigated();
        menCategoriesPage.AddItemsFromMensCategoryWithPriceLessThan50USD();
        menCategoriesPage.assertSuccessfulAddToCardMessageIsPresent();
        menCategoriesPage.assertProductShirtIsPresent();
        actions.assertItemQuantityInCart("Polo T Shirt", 1);
        actions.assertItemPresentInCart("Polo T Shirt");
        actions.assertTotalPrice("$10.99");
        menCategoriesPage.deleteItemFromCard();
        menCategoriesPage.assertSuccessfulDeleteItemMessageIsPresent();
    }

    @Test
    @Description("SDP-41 [Shopping Cart] Add items to the shopping cart from women's category")
    public void shouldAddItemsToCartFromWomensCategory() {
        womenCategoriesPage.navigateToPage();
        womenCategoriesPage.assertPageNavigated();
        womenCategoriesPage.addItemsToCartFromWomenSCategory();
        womenCategoriesPage.assertSuccessfulAddToCardMessageIsPresent();
        womenCategoriesPage.assertProductDressIsPresent();
        actions.assertItemQuantityInCart("Floral Wrap Dress", 1);
        actions.assertItemPresentInCart("Floral Wrap Dress");
        actions.assertTotalPrice("$71.99");
        womenCategoriesPage.deleteItemFromCard();
        womenCategoriesPage.assertSuccessfulDeleteItemMessageIsPresent();
    }

    @Test
    @Description("SDP-42 [Shopping Cart] Add items to the shopping cart from women's category with red color")
    public void shouldAddRedItemsFromWomensCategoryToCart() {
        womenCategoriesPage.navigateToPage();
        womenCategoriesPage.assertPageNavigated();
        womenCategoriesPage.addRedItemsToCartFromWomenCategory();
        womenCategoriesPage.assertSuccessfulAddToCardMessageIsPresent();
        womenCategoriesPage.assertProductCropTopIsPresent();
        actions.assertItemQuantityInCart("Crop Top With Tie", 1);
        actions.assertItemPresentInCart("Crop Top With Tie");
        actions.assertTotalPrice("$26.99");
        womenCategoriesPage.deleteItemFromCard();
        womenCategoriesPage.assertSuccessfulDeleteItemMessageIsPresent();
    }

    @Test
    @Description("SDP-43 [Shopping Cart] Add items to the shopping cart from women's category with size S")
    public void shouldAddSizeSItemsFromWomensCategoryToCart() {
        womenCategoriesPage.navigateToPage();
        womenCategoriesPage.assertPageNavigated();
        womenCategoriesPage.addSmallSizeItemToCartFromWomensCategory();
        womenCategoriesPage.assertSuccessfulAddToCardMessageIsPresent();
        womenCategoriesPage.assertSizeSFlaredSkirtPrice(PRODUCT_FLARED_SKIRT_UNIT_PRICE, "$34.99");
        actions.assertItemQuantityInCart("Flared Skirt", 1);
        actions.assertItemPresentInCart("Flared Skirt");
        actions.assertTotalPrice("$34.99");
        womenCategoriesPage.deleteItemFromCard();
        womenCategoriesPage.assertSuccessfulDeleteItemMessageIsPresent();
    }

    @Test
    @Description("SDP-44 [Shopping Cart] Add items to the shopping cart from women's Category with price " +
            "$50 - $100 USD")
    public void shouldAddItemsToCartFromWomensCategoryWithinPriceRange() {
        womenCategoriesPage.navigateToPage();
        womenCategoriesPage.assertPageNavigated();
        womenCategoriesPage.addItemsToCartFromWomensCategoryWithinPriceRange();
        womenCategoriesPage.assertSuccessfulAddToCardMessageIsPresent();
        womenCategoriesPage.assertStripedShirtPrice(PRODUCT_STRIPED_SHIRT_UNIT_PRICE, "$71.99");
        actions.assertItemQuantityInCart("Striped Shirt", 1);
        actions.assertItemPresentInCart("Striped Shirt");
        actions.assertTotalPrice("$71.99");
        womenCategoriesPage.deleteItemFromCard();
        womenCategoriesPage.assertSuccessfulDeleteItemMessageIsPresent();
    }

    @Test
    @Description("SDP-45 [Shopping Cart] Add items to the shopping cart from sportswear category")
    public void shouldAddItemsToCartFromSportWearCategory() {
        sportswearCategoriesPage.navigateToPage();
        sportswearCategoriesPage.assertPageNavigated();
        sportswearCategoriesPage.addItemsToCartFromSportWearCategory();
        sportswearCategoriesPage.assertSuccessfulAddToCardMessageIsPresent();
        sportswearCategoriesPage.assertRunningSweatshirtPrice(PRODUCT_RUNNING_SWEATSHIRT_UNIT_PRICE,
                "$78.99");
        actions.assertItemQuantityInCart("Running Sweatshirt", 1);
        actions.assertItemPresentInCart("Running Sweatshirt");
        actions.assertTotalPrice("$78.99");
        sportswearCategoriesPage.deleteItemFromCard();
        sportswearCategoriesPage.assertSuccessfulDeleteItemMessageIsPresent();
    }

    @Test
    @Description("SDP-46 [Shopping Cart] Add items to the shopping cart from sportswear category with black color")
    public void shouldAddBlackColorItemsFromSportswearCategoryToCart() {
        sportswearCategoriesPage.navigateToPage();
        sportswearCategoriesPage.assertPageNavigated();
        sportswearCategoriesPage.addBlackItemsToCartFromSportswearCategory();
        sportswearCategoriesPage.assertSuccessfulAddToCardMessageIsPresent();
        sportswearCategoriesPage.assertShortPantsPrice(PRODUCT_SHORT_PANTS_UNIT_PRICE, "$82.99");
        actions.assertItemQuantityInCart("Short Pants", 1);
        actions.assertItemPresentInCart("Short Pants");
        actions.assertTotalPrice("$82.99");
        sportswearCategoriesPage.deleteItemFromCard();
        sportswearCategoriesPage.assertSuccessfulDeleteItemMessageIsPresent();
    }

    @Test
    @Description("SDP-47 [Shopping Cart] Add items to the shopping cart from sportswear category with size M")
    public void shouldAddMSizesItemsFromSportswearCategoryToCart() {
        sportswearCategoriesPage.navigateToPage();
        sportswearCategoriesPage.assertPageNavigated();
        sportswearCategoriesPage.addSizeMediumItemsToCartFromSportswearCategory();
        sportswearCategoriesPage.assertSuccessfulAddToCardMessageIsPresent();
        sportswearCategoriesPage.assertLacedCropTopPrice(PRODUCT_LACED_CROP_TOP_UNIT_PRICE, "$63.99");
        actions.assertItemQuantityInCart("Laced Crop Top", 1);
        actions.assertItemPresentInCart("Laced Crop Top");
        actions.assertTotalPrice("$63.99");
        sportswearCategoriesPage.deleteItemFromCard();
        sportswearCategoriesPage.assertSuccessfulDeleteItemMessageIsPresent();
    }
}
