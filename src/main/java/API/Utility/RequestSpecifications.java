package API.Utility;

import io.restassured.*;
import io.restassured.specification.RequestSpecification;

import static API.DATA.Urls.*;


public class RequestSpecifications {

        public <T> RequestSpecification request(String username, String pass, T pojo){
                RequestSpecification req= RestAssured.given()
                        .baseUri(BASEURL)
                        .auth().basic(username, pass)
                        .header("Content-Type", "application/json; charset=utf-8")
                        .basePath(BASEPATH);
                return (pojo == null)? req: req.body(pojo);
        }
}
