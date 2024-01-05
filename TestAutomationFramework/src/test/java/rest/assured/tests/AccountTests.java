package rest.assured.tests;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static com.telerikacademy.testframework.api.utils.Endpoints.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTests extends BaseSetupMethods {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testCreateUpdateDeleteAddress() {
        String token = createRefreshToken(LANDON_BUTLER_USERNAME, LANDON_BUTLER_PASSWORD);

        // Create new Address
        JSONObject createRequestBody = createRequestBodyForNewAddress();
        JSONObject createResponse = createAddress(token, createRequestBody);
        int addressId = createResponse.getJSONObject("data").getInt("id");
        assertEquals("Mark", createResponse.getJSONObject
                ("data").getJSONObject("attributes").getString("firstname"));
        assertEquals("Winterburn", createResponse.getJSONObject
                ("data").getJSONObject("attributes").getString("lastname"));

        // Update Address
        JSONObject addressUpdateDetails = new JSONObject();
        prepareUpdateAddressRequestBody(addressUpdateDetails);
        JSONObject updateResponse = updateAddress(token, addressId, addressUpdateDetails);
        assertEquals("Stephen", updateResponse.getJSONObject
                ("data").getJSONObject("attributes").getString("firstname"));
        assertEquals("234 Old Georgetown Road", updateResponse.getJSONObject
                ("data").getJSONObject("attributes").getString("address1"));

        // Delete Address
        Response deleteResponse = deleteAddress(token, addressId);
        assertEquals(204, deleteResponse.getStatusCode(),
                "Expected status code 204 for delete operation");
    }

    @Test
    public void testListUserAddresses() {
        String token = createRefreshToken(G_USERNAME, G_PASSWORD);

        Response response = listUserAddress(token);
        String email = JsonPath.from(response.asString()).getString("data.attributes.email");
        assertEquals("g@abv.bg", email, "Email does not match expected value");
    }


}
