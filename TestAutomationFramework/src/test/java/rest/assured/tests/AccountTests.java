package rest.assured.tests;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AccountTests extends BaseSetupMethods {


    @Test
    public void executeGetAddressesListTest() {
        Response response = super.testGetAddressesList();

        JSONObject jsonResponse = new JSONObject(response.asString());
        assertEquals("Heidi", jsonResponse.getJSONArray("data").
                getJSONObject(0).getJSONObject("attributes").getString("firstname"));
        assertEquals("New York", jsonResponse.getJSONArray("data").
                getJSONObject(0).getJSONObject("attributes").getString("city"));
    }



}
