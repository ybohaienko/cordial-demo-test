package com.cordial.cordialdemotest;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.cordial.cordialdemotest.util.Commons.supplyIntegersListOfSize;
import static org.testng.Assert.assertEquals;

@Test(groups = "load")
public class PerformanceTest extends RestApiTestStarter {

	@Test(priority = 3)
	public void whenThousandNumbersPayloadThenSortPass() {
		List<Integer> payload = supplyIntegersListOfSize(100_000);
		List<Object> expectedSortedPayload = payload.stream()
				.sorted()
				.collect(Collectors.toList());

		assertEquals(
				postPayloadOutputList(payload),
				expectedSortedPayload
		);
	}

	@Test(priority = 4)
	public void whenMillionNumbersPayloadThenSortFail() {
		List<Integer> payload = supplyIntegersListOfSize(1_000_000);
		postPayloadOutputList(payload, HttpStatus.SC_REQUEST_TOO_LONG);
	}
}
