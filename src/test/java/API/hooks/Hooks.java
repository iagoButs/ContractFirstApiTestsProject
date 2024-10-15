package API.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import zephyrScale.SendResultToJira;

public class Hooks {
    SendResultToJira sendResultToJira=new SendResultToJira();
    @After
    public void afterScenario(Scenario scenario){
        var testCaseId= scenario.getSourceTagNames().stream()
                .filter(tag -> tag.startsWith("@DP-"))
                .findFirst()
                .map(tag -> tag.replace("@", ""))
                .orElse("No Test Case ID");
        if (scenario.isFailed()) {
            sendResultToJira.sendAutomatedStatusToJira(testCaseId, "Fail","DP-R3");
        } else {
            sendResultToJira.sendAutomatedStatusToJira(testCaseId, "Pass","DP-R3");
        }
    }

}
