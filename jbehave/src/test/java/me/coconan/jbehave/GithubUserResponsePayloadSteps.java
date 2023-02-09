package me.coconan.jbehave;

import org.apache.http.HttpResponse;
import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;

import static me.coconan.jbehave.GithubUserNotFoundSteps.getGithubUserProfile;
import static org.junit.Assert.assertThat;

public class GithubUserResponsePayloadSteps {

    private String api;
    private GithubUser resource;

    @Given("github user profile api")
    public void givenGithubUserProfileApi() {
        api = "https://api.github.com/users/%s";
    }

    @When("I look for parameterized $user via the api")
    public void whenILookForEugenpViaTheApi(String user) throws IOException {
        HttpResponse httpResponse = getGithubUserProfile(api, user);
        resource = RetrieveUtil.retrieveResourceFromResponse(httpResponse, GithubUser.class);
    }

    @Then("github's response contains a 'login' payload same as $username")
    public void thenGithubsResponseContainsALoginPayloadSameAsEugenp(String username) {
        assertThat(username, Matchers.is(resource.getLogin()));
    }
}
