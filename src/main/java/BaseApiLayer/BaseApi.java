package BaseApiLayer;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

	protected static RequestSpecification request;
	public static void reqSpec()
	{
	      request=RestAssured.given().contentType(ContentType.JSON).log().all();
	}
}
