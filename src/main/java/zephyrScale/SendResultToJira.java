package zephyrScale;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static API.DATA.AuthenticationCredentials.jiraToken;

public class SendResultToJira  {


    public String createNewTestCycle(){
        var body=new CycleModel("DP", "newCycle");
        Response res= RestAssured.given()
                .header("Authorization", "Bearer " + jiraToken)
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .baseUri("https://api.zephyrscale.smartbear.com/v2/testcycles")
                .body(body)
                .post();
        return res.body().jsonPath().getString("key");
    }

     public void sendAutomatedStatusToJira(String testCaseKey, String status, String cycleId){
        if (cycleId==null) {
            cycleId = createNewTestCycle();
        }
         ExecutionModel body=new ExecutionModel("DP", testCaseKey, cycleId, status);
         RestAssured.given()
                 .header("Authorization", "Bearer " + jiraToken)
                 .relaxedHTTPSValidation()
                 .contentType(ContentType.JSON)
                 .baseUri("https://api.zephyrscale.smartbear.com/v2/testexecutions")
                 .body(body)
                 .post();
     }
}
