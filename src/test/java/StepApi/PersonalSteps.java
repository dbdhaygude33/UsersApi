package StepApi;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hamcrest.Matchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import BaseApiLayer.BaseApi;
import Model.Address;
import Model.Personal;
import Reader.PropertyReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class PersonalSteps extends BaseApi {

	static String requestPayload;
	static Response response;
	static String personalId;
	static ValidatableResponse valres;
	
	@Given("set base uri")
	public void set_base_uri() throws Exception {
		RestAssured.baseURI=PropertyReader.getProperty("BASE_URI");
	}

	@Given("set as {string} base path")
	public void set_as_base_path(String basePath) {
		RestAssured.basePath=basePath;
	}

	@Given("get the request specification object")
	public void get_the_request_specification_object() {
		BaseApi.reqSpec();
	}

	@Given("create request body for post request")
	public void create_request_body_for_post_request() throws Exception {
		Address add= new Address();
		add.setStreet("sinhgad road");
		add.setArea("Kothrud");
		add.setCity("Pune");
		add.setState("MH");
		add.setCountry("India");
		add.setPincode(332244);
		
		Personal personal= new Personal();
		personal.setFirstName("Rohit");
		personal.setLastName("Patil");
		
		ArrayList<String> topics= new ArrayList<String>();
		topics.add("UI Automation");
		topics.add("Api Automation");
		topics.add("Selenium");
		topics.add("Java");
		topics.add("Cucumber");
		
		personal.setTopics(topics);
		
		ArrayList<Integer> fees= new ArrayList<Integer>();
		fees.add(20000);
		fees.add(3000);
		fees.add(60000);
		fees.add(87644);
		
		personal.setFees(fees);
		personal.setAddress(add);
		
		ObjectMapper mapper= new ObjectMapper();
	    requestPayload=mapper.writeValueAsString(personal);


	}

	@Given("attach request payload to http post request")
	public void attach_request_payload_to_http_post_request() {
		
		request.body(requestPayload);
	}

	@When("select post request")
	public void select_post_request() {
	 response=request.post();
	}

	@Then("capture personal id from response body")
	public void capture_personal_id_from_response_body() {
		
	 personalId=response.jsonPath().getString("id");

	}

	@Then("get validatable response interface object")
	public void get_validatable_response_interface_object() {
	 valres=response.then().assertThat();
	}

	@Then("validate status code as {int}")
	public void validate_status_code_as(Integer code) {

          valres.statusCode(Matchers.equalTo(code));
	}

	@Then("validate status line as {string}")
	public void validate_status_line_as(String line) {

          valres.statusLine(Matchers.containsString(line));
	}

	@Then("validate response time below {int} ms")
	public void validate_response_time_below_ms(Integer time) {

          valres.time(Matchers.lessThan((long)time));
	}

	@Then("validate {string} and {string} reponse header")
	public void validate_and_reponse_header(String keyName, String value) {

          valres.header(keyName, Matchers.containsString(value));
	}

	@Then("validate {string} and value as current month year response header")
	public void validate_and_value_as_current_month_year_response_header(String keyName) {
		SimpleDateFormat simple= new SimpleDateFormat("MMM yyyy");
		Date date =new Date();
		String monthYear=simple.format(date);
		
		valres.header(keyName, Matchers.containsString(monthYear));
	}

	@Then("validate json schema")
	public void validate_json_schema() throws Exception {
		String path=System.getProperty("user.dir")+"/src/test/resources/SchemaValidater/schemavalidater.json";
	
		FileReader file= new FileReader(path);
		
		valres.body(JsonSchemaValidator.matchesJsonSchema(file));	

	}

	@Then("generate response logs")
	public void generate_response_logs() {
		valres.log().all();
	}
	
	@Given("set id as a path param")
	public void set_id_as_a_path_param() {
	  request.pathParam("id", personalId);
	}
	@When("select get request")
	public void select_get_request() {
	  response= request.get("{id}");
	}

}
