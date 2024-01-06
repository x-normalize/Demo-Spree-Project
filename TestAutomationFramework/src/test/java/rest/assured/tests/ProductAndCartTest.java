package rest.assured.tests;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.PRODUCT_DENIM_SHIRT_NAME;
import static com.telerikacademy.testframework.api.utils.Constants.PRODUCT_DENIM_SHIRT_PRICE;
import static com.telerikacademy.testframework.api.utils.Endpoints.BASE_URL;
import static com.telerikacademy.testframework.api.utils.Endpoints.PRODUCT_DENIM_SHIRT_ENDPOINT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductAndCartTest extends BaseSetupMethods {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Description("SDP-37 [Shopping Cart] Add items to the shopping cart from men's category")
    public void testAddItemToCartFromMenCategory() {

        // Create a new cart and get its token
        JSONObject cartResponse = createCart();
        String cartToken = cartResponse.getJSONObject("data").getJSONObject("attributes").getString("token");

        // Add an item to the cart
        Response addItemResponse = addItemToCart(cartToken, "117", 2);
        assertEquals(200, addItemResponse.getStatusCode(), "Expected status code 200");

        // Validate carts details
        JSONObject jsonResponse = new JSONObject(addItemResponse.asString());
        int itemCount = jsonResponse.getJSONObject("data").getJSONObject("attributes").getInt("item_count");
        String itemTotal = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("item_total");
        String currency = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("currency");

        assertEquals(2, itemCount, "Item count does not match expected value");
        assertEquals("121.98", itemTotal);
        assertEquals("USD", currency);

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    @Description("SDP-40 [Shopping Cart] Add items to the shopping cart from men's category with price less than " +
            "$50 USD")
    public void testAddItemsFromMensCategoryToCartWithPriceLessThan50USD() {

        // Create a new cart and get its token
        JSONObject cartResponse = createCart();
        String cartToken = cartResponse.getJSONObject("data").getJSONObject("attributes").getString("token");

        // Add an item to the cart with price 10.99
        Response addItem = addItemToCart(cartToken, "128", 1);
        assertEquals(200, addItem.getStatusCode(), "Expected status code 200");

        // Validate card details
        JSONObject jsonResponse = new JSONObject(addItem.asString());
        int itemCount = jsonResponse.getJSONObject("data").getJSONObject("attributes").getInt("item_count");
        String itemTotal = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("item_total");
        String currency = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("currency");

        assertEquals(1, itemCount, "Item count does not match expected value");
        assertEquals("10.99", itemTotal);
        assertEquals("USD", currency);

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    @Description("SDP-41 [Shopping Cart] Add items to the shopping cart from women's category")
    public void testAddItemFromWomanCategory() {

        // Create a new cart and get its token
        JSONObject cartResponse = createCart();
        String cartToken = cartResponse.getJSONObject("data").getJSONObject("attributes").getString("token");

        // Add item Floral Wrap Dress to the shopping cart from women's category
        Response addItem = addItemToCart(cartToken, "162", 1);
        assertEquals(200, addItem.getStatusCode(), "Expected status code 200");

        // Validate card details
        JSONObject jsonResponse = new JSONObject(addItem.asString());
        int itemCount = jsonResponse.getJSONObject("data").getJSONObject("attributes").getInt("item_count");
        String itemTotal = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("item_total");
        String currency = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("currency");

        assertEquals(1, itemCount, "Item count does not match expected value");
        assertEquals("71.99", itemTotal);
        assertEquals("USD", currency);

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    @Description("SDP-44 [Shopping Cart] Add items to the shopping cart from women's Category with price " +
            "$50 - $100 USD")
    public void testAddItemsToCartFromWomenCategoryWithinPriceRange() {

        // Create a new cart and get its token
        JSONObject cartResponse = createCart();
        String cartToken = cartResponse.getJSONObject("data").getJSONObject("attributes").getString("token");

        // Add items Striped Shirt to the shopping cart from women's category
        Response addItem = addItemToCart(cartToken, "167", 2);
        assertEquals(200, addItem.getStatusCode(), "Expected status code 200");

        // Validate card details
        JSONObject jsonResponse = new JSONObject(addItem.asString());
        int itemCount = jsonResponse.getJSONObject("data").getJSONObject("attributes").getInt("item_count");
        String itemTotal = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("item_total");
        String currency = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("currency");

        assertEquals(2, itemCount, "Item count does not match expected value");
        assertEquals("139.98", itemTotal);
        assertEquals("USD", currency);

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    @Description("SDP-45 [Shopping Cart] Add items to the shopping cart from sportswear category")
    public void testAddItemsToCartFromSportWearCategory() {

        // Create a new cart and get its token
        JSONObject cartResponse = createCart();
        String cartToken = cartResponse.getJSONObject("data").getJSONObject("attributes").getString("token");

        // Add items to the shopping cart from sportswear category
        Response addItem = addItemToCart(cartToken, "222", 1);
        assertEquals(200, addItem.getStatusCode(), "Expected status code 200");

        // Validate card details
        JSONObject jsonResponse = new JSONObject(addItem.asString());
        int itemCount = jsonResponse.getJSONObject("data").getJSONObject("attributes").getInt("item_count");
        String itemTotal = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("item_total");
        String currency = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("currency");

        assertEquals(1, itemCount, "Item count does not match expected value");
        assertEquals("51.99", itemTotal);
        assertEquals("USD", currency);

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    @Description("Delete item from the shopping cart")
    public void testDeleteItemsFromShoppingCart() {
        // Create a new cart and get its token
        JSONObject cartResponse = createCart();
        String cartToken = cartResponse.getJSONObject("data").getJSONObject("attributes").getString("token");

        // Add item to the shopping cart
        Response addItem = addItemToCart(cartToken, "117", 1);
        assertEquals(200, addItem.getStatusCode(), "Expected status code 200");

        // Extract the line item ID from the addItem response
        String lineItemId = extractTheItemID(addItem);

        // Delete Item from the shopping cart
        Response delete = deleteItemToCart(cartToken, lineItemId);
        assertEquals(200, delete.getStatusCode(), "Expected status code 200");
    }

    @Test
    public void testListAllProducts() {
        Response response = listAllProducts();
        JSONObject jsonResponse = new JSONObject(response.asString());

        assertEquals(200, response.getStatusCode(), "Expected status code 200");
        JSONArray productsArray = jsonResponse.getJSONArray("data");
        assertFalse(productsArray.isEmpty(), "Product list should not be empty");

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    public void testFetchSingleProductData() {
        Response response = singleProductData(PRODUCT_DENIM_SHIRT_ENDPOINT);
        JSONObject jsonResponse = new JSONObject(response.asString());

        assertEquals(200, response.getStatusCode(), "Expected status code 200");
        assertEquals(PRODUCT_DENIM_SHIRT_NAME, jsonResponse.getJSONObject("data").getJSONObject
                ("attributes").getString("name"), "The product name does not match the expected value.");
        String actualPrice = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("price");
        assertEquals(PRODUCT_DENIM_SHIRT_PRICE, actualPrice, "The product price does not match the expected value.");

        System.out.println(jsonResponse.toString(4));
    }


}
