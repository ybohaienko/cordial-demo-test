package com.sorting.sortingdemotest;

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

	@Value("${sorting.api.url}")
	private String baseApiUrl;
	private final String NUMBERS_ARRAY_PATH = "numbers";

	protected List<Object> postPayloadOutputList(List<?> payload) {
		return postPayloadOutputList(payload, HttpStatus.SC_OK)
				.body()
				.jsonPath()
				.getList(NUMBERS_ARRAY_PATH);
	}

	protected Response postPayloadOutputList(List<?> payload, int statusCode) {
		Response response = postPayload(payload);
		assertEquals(response.statusCode(), statusCode,
				"Response status code in not as expected\n" +
						"Response message: " + response.body().asString());
		return response;
	}

	protected Response postPayload(List<?> payload) {
		JSONObject requestBody = createRequestBody(payload);
		return given()
				.contentType(ContentType.JSON)
				.when()
				.body(requestBody.toString())
				.post(baseApiUrl);
	}

	private JSONObject createRequestBody(List testDataSet) {
		JSONObject requestBody = new JSONObject();
		try {
			requestBody.put(NUMBERS_ARRAY_PATH, new JSONArray(testDataSet));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return requestBody;
	}
}
