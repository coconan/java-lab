package me.coconan.karate;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.intuit.karate.junit4.Karate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(Karate.class)
@CucumberOptions(features = "classpath:Feature")
public class KarateIntegrationTest {
    private static final int PORT_NUMBER = 8097;
    private static final WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(PORT_NUMBER));

    @BeforeClass
    public static void setUp() throws Exception {
        wireMockServer.start();

        configureFor("localhost", PORT_NUMBER);
        stubFor(get(urlEqualTo("/user/get"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":\"1234\",name:\"John Smith\"}")));
        stubFor(post(urlEqualTo("/user/create"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(containing("id"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":\"1234\",name:\"John Smith\"}")));
    }

    @AfterClass
    public static void tearDown() throws Exception {
        wireMockServer.stop();
    }
}
