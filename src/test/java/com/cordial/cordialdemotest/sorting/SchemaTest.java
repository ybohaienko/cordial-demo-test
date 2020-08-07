package com.sorting.sortingdemotest.sorting;

import com.sorting.sortingdemotest.RestApiTestStarter;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static com.sorting.sortingdemotest.util.Commons.supplyIntegersListOfSize;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Test(groups = "schema")
public class SchemaTest extends RestApiTestStarter {

	@Test(priority = 1)
	public void whenPostNumbersArrayThenValidateJsonSchema() {
		List<Integer> payload = supplyIntegersListOfSize(3);
		Response response = postPayload(payload);

		response.then()
				.assertThat().
				body(matchesJsonSchemaInClasspath("numbersSchema.json"));
	}
}
