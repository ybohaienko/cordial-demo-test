package com.cordial.cordialdemotest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@SpringBootTest
public class RestApiTestStarter extends AbstractTestNGSpringContextTests {

	@Value("${cordial.api.url}")
	private String baseApiUrl;

	List<Object> postPayloadOutputList(List<?> payload) {
		return postPayloadOutputList(payload, HttpStatus.SC_OK)
				.body()
				.jsonPath()
				.getList("numbers");
	}

	Response postPayloadOutputList(List<?> payload, int statusCode) {
		Response response = postPayload(payload);
		assertEquals(response.statusCode(), statusCode,
				"Response status code in not as expected\n" +
						"Response message: " + response.body().asString());
		return response;
	}

	private Response postPayload(List<?> payload) {
		JSONObject requestBody = createRequestBody(payload);
		return given().
				contentType(ContentType.JSON).
				when().
				body(requestBody.toString()).
				post(baseApiUrl);
	}

	private JSONObject createRequestBody(List testDataSet) {
		JSONObject requestBody = new JSONObject();
		try {
			requestBody.put("numbers", new JSONArray(testDataSet));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return requestBody;
	}

	private JSONObject createRequestBody(Object[] testDataSet) {
		JSONObject requestBody = new JSONObject();
		try {
			requestBody.put("numbers", new JSONArray(testDataSet));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return requestBody;
	}
}
