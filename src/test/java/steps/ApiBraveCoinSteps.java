package steps;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.*;

import static io.restassured.RestAssured.given;


public class ApiBraveCoinSteps {
    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    @Given("^I've a valid key the (.+)$")
    public void setRequestParams(String URI){
        request = given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .header("X-RapidAPI-Host","bravenewcoin.p.rapidapi.com")
                .header("X-RapidAPI-Key", "db113b8394mshfa004e691262888p1c26bbjsnb028680dd9f0")
                .baseUri(URI).log().all();
    }

    @When("^I send a POST request with a valid (.+) body to the (.+)$")
    public void sendPostRequest(String payload, String endpoint){
        File requestBody = new File(String.format("src/test/resources/payloads/%s.json", payload));
        response = request.when().body(requestBody).post(endpoint).prettyPeek();
    }

    @Then("^I can receive a token in the response$")
    public void validateTheToken(){

    }

}
