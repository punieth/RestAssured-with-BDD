package stepdefs;

import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class WeatherStepDefinitions {

	private Response response;
	private RequestSpecification request;

	@Given("^city name \"([^\"]*)\" and API key \"([^\"]*)\" as a query parameter$")
	public void city_name_and_API_key_as_a_query_parameter(String arg1, String arg2) throws Throwable {
		request=given()
		.queryParam("q", "London,uk")
		.queryParam("appid", "54f92cf245d2ccf9185486bc88e2d0e9");
	}

	@When("^a user visit to \"([^\"]*)\"$")
	public void a_user_visit_to(String arg1) throws Throwable {
		response = request.when().get(arg1);
	}

	@Then("^response the status code is (\\d+) response time is less then (\\d+) ms$")
	public void response_the_status_code_is_response_time_is_less_then_ms(int arg1, Long arg2) throws Throwable {
		response.then().statusCode(arg1);
		response.then().contentType(ContentType.JSON);
		response.then().time(Matchers.lessThan(arg2));
	}
	
	@Then("^response body includes \"([^\"]*)\" is equal to \"([^\"]*)\" and \"([^\"]*)\" is equal to (\\d+)$")
	public void response_body_includes_is_equal_to_and_is_equal_to(String arg1, String arg2, String arg3, int arg4) throws Throwable {
		response.then().body(arg1, Matchers.equalTo(arg2));
		response.then().body(arg3, Matchers.equalTo(arg4));
	}
}


