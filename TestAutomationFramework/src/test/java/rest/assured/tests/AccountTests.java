package rest.assured.tests;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.LANDON_BUTLER_PASSWORD;
import static com.telerikacademy.testframework.api.utils.Constants.LANDON_BUTLER_USERNAME;
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
        JSONObject createRequestBody = createRequestBodyForNewAddress();

        JSONObject createResponse = createAddress(token, createRequestBody);
        int addressId = JsonPath.from(createResponse.toString()).getInt("data.id");

        assertEquals("Mark", createResponse.getJSONObject
                ("data").getJSONObject("attributes").getString("firstname"));
        assertEquals("Winterburn", createResponse.getJSONObject
                ("data").getJSONObject("attributes").getString("lastname"));

        JSONObject addressUpdateDetails = new JSONObject();
        createRequestBodyForUpdateAddress(addressUpdateDetails);

        JSONObject updateResponse = updateAddress(token, addressId, addressUpdateDetails);
        assertEquals("Stephen", updateResponse.getJSONObject
                ("data").getJSONObject("attributes").getString("firstname"));
        assertEquals("234 Old Georgetown Road", updateResponse.getJSONObject
                ("data").getJSONObject("attributes").getString("address1"));

        deleteAddress(token, addressId);
    }


}
