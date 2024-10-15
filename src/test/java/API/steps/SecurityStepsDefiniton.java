package API.steps;

import API.Utility.RequestSpecifications;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import zephyrScale.SendResultToJira;


public class SecurityStepsDefiniton extends RequestSpecifications {
    Response response;
    @Given("send get Request with credentials: {string}, {string}")
    public void sendGetRequestWithWrongCredentials(String username, String pass){
        response=request(username, pass, null).get();
    }
    @Then("status code should be {int}")
    public void statusCodeCheck(int statusCode){
        Assert.assertEquals(response.statusCode(), statusCode);
    }

    @Then("statusline should contains String: Unauthorized")
    public void checkStatuslineString(){
        String statusLine=response.getStatusLine();
        Assert.assertTrue(statusLine.contains("Unauthorized"));
    }
}
