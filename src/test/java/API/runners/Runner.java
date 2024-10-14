package API.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/api-features",
        glue = "API/steps"
//        plugin = {
//        "pretty",
//        "html:target/cucumber-html-report.html",
//        "json:target/cucumber-report.json"
//}
)
public class Runner extends AbstractTestNGCucumberTests {
}
