package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class ApiSteps {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    @Given("^I send a get request to (.+)$")
    public void sendGetReq(String URI){
        request = given().
                baseUri(URI).
                contentType(ContentType.JSON);
    }

    @Then("^I get a (\\d+) status code$")
    public void validateStatus(int code){
        response = request
                .when()
                .get("/users/luis-peralta/repos");
        json = response.then().statusCode(code);
    }

    @Then("^I validate there are (\\d+) items on the (.+) endpoint$")
    public void validateSize(int size, String endpoint){
        response = request
                .when()
                    .get(endpoint);
                List<String> jsonRes = response.jsonPath().getList("$");
                int actualSize = jsonRes.size();
        assertEquals(size, actualSize);
    }

    @Then("^I validate there is a value: (.+) in the response at (.+) endpoint$")
    public void validateName(String name, String endpoint){
        response = request
                .when()
                    .get(endpoint);
        List<String> jsonRes = response.jsonPath().getList("username");
        assertTrue(String.format("The name %s no exists",name),jsonRes.contains(name));
    }

    @Then("^I validate there is a value street: (.+) in the response at (.+) endpoint$")
    public void validateStreet(String street, String endpoint){
        response = request
                .when()
                .get(endpoint);
        List<String> jsonRes = response.jsonPath().getList("address.street");
        assertTrue(String.format("The street %s no exists",street),jsonRes.contains(street));
    }

}
