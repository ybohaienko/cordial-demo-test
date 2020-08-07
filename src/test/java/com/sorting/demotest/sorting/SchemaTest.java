package com.sorting.demotest.sorting;

import com.sorting.demotest.RestApiTestStarter;
import com.sorting.demotest.util.Commons;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Test(groups = "schema")
public class SchemaTest extends RestApiTestStarter {

	@Test(priority = 1)
	public void whenPostNumbersArrayThenValidateJsonSchema() {
		List<Integer> payload = Commons.supplyIntegersListOfSize(3);
		Response response = postPayload(payload);

		response.then()
				.assertThat().
				body(matchesJsonSchemaInClasspath("numbersSchema.json"));
	}
}
