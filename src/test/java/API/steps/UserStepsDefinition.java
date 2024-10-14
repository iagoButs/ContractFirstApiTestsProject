package API.steps;

import API.Utility.RequestSpecifications;
import API.models.RegisteredUsers;
import API.models.UserPersonalIdDocument;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;


import java.util.Map;

import static API.DATA.AuthenticationCredentials.*;

public class UserStepsDefinition extends RequestSpecifications {
    RegisteredUsers user;
    Response response;


    @Given("set users data: {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void setUserForCreate(String id, String firstName, String lastName, String email, String dateOfBirth,
                        String documentId, String countryOfIssue, String validUntil) {
        var personalIdDocument = new UserPersonalIdDocument(documentId, countryOfIssue, validUntil);
        user = new RegisteredUsers(id, firstName, lastName, email, dateOfBirth, personalIdDocument);
    }

    @Given("set users data for update: {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void setUserDataForUpdate(String id, String firstName, String lastName, String email, String dateOfBirth,
                        String documentId, String countryOfIssue, String validUntil) {
        var personalIdDocument = new UserPersonalIdDocument(documentId, countryOfIssue, validUntil);
        user = new RegisteredUsers(id, firstName, lastName, email, dateOfBirth, personalIdDocument);
    }

    @When("send the PUT request to update User with user id {string}")
    public void updateUser(String userId){
        response=request(userName, password, user).put(userId);
        response.then().log().all();
    }


    @When("send the POST request to create User")
    public void sendPostRequestToCreateUser(){
        response=request(userName, password, user).post();
    }

    @Then("if StatusCode Is 400, validate the error message")
    public void validateErrorMessage(){
        if (response.statusCode()==400){
           var detailText=response.body().jsonPath().getString("detail");
           Assert.assertEquals(detailText, "The countryOfIssue must be an ISO 3166-1 alpha-2 code.");
        }
    }

    @Then("the Response status code should be {int}")
    public void statusCodeCheck(int statusCode){
        Assert.assertEquals(response.statusCode(), statusCode);
    }

    @Given("send the GET request with user Id {string} to retrieve user informacion")
    public void sendPostRequestToRetrieveUserData(String userId){
        response=request(userName, password,null).get(userId);
        user= response.as(RegisteredUsers.class);
    }


    @Given("send delete Request with userId {string}")
    public void deleteUserById(String id){
        response=request(userName, password, null).delete(id);
        response.then().log().all();
    }



    @Then("the response should contain this user details:")
    public void compareUserDetails(DataTable dataTable){
        Map<String, String> expectedUserDetails = dataTable.asMap(String.class, String.class);
        Assert.assertEquals(user.getId(), expectedUserDetails.get("id"));
        Assert.assertEquals(user.getFirstName(), expectedUserDetails.get("firstName"));
        Assert.assertEquals(user.getLastName(), expectedUserDetails.get("lastName"));
        Assert.assertEquals(user.getEmail(), expectedUserDetails.get("email"));
        Assert.assertEquals(user.getDateOfBirth(), expectedUserDetails.get("dateOfBirth"));
        Assert.assertEquals(user.getPersonalIdDocument().getDocumentId(), expectedUserDetails.get("documentId"));
        Assert.assertEquals(user.getPersonalIdDocument().getCountryOfIssue(), expectedUserDetails.get("countryOfIssue"));
        Assert.assertEquals(user.getPersonalIdDocument().getValidUntil(), expectedUserDetails.get("validUntil"));
    }

}
