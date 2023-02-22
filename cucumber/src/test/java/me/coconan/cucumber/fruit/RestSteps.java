package me.coconan.cucumber.fruit;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import me.coconan.cucumber.fruit.hooks.ServerHooks;

import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;


public class RestSteps {
    private ClientResponse response;

    @When("the client request GET \\/fruits")
    public void theClientRequestGETFruits() {
        try {
            Client client = Client.create();

            WebResource webResource = client.resource("http://localhost:" + ServerHooks.PORT + "/fruits");

            response = webResource.type("application/json")
                    .get(ClientResponse.class);
        } catch (RuntimeException r) {
            throw r;
        } catch (Exception e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }

        assertEquals("Did not receive OK response: ", HttpURLConnection.HTTP_OK, response.getStatus());
    }

    @Then("the response should be JSON:")
    public void theResponseShouldBeJSON(String jsonExpected) {
        assertEquals("Incorrect JSON representation.",
                jsonExpected, response.getEntity(String.class));
    }
}
