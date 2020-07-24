package com.cordial.cordialdemotest;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.cordial.cordialdemotest.util.Commons.*;
import static org.testng.Assert.assertEquals;

@Test(groups = "load")
public class PerformanceTest extends RestApiTestStarter {

	@Test(priority = 3)
	public void when100KIntegersSortedPayloadThenSortPass() {
		List<Integer> payload = supplyIntegersListOfSize(100_000).stream()
				.sorted()
				.collect(Collectors.toList());

		assertEquals(
				postPayloadOutputList(payload),
				payload
		);
	}

	@Test(priority = 3)
	public void when100KIntegersRandomOrderPayloadThenSortPass() {
		List<Integer> payload = supplyIntegersListOfSize(100_000);
		List<Object> expectedSortedPayload = payload.stream()
				.sorted()
				.collect(Collectors.toList());

		assertEquals(
				postPayloadOutputList(payload),
				expectedSortedPayload
		);
	}

	@Test(priority = 3)
	public void when100KIntegersReverseOrderPayloadThenSortPass() {
		List<Integer> payload = supplyIntegersListOfSize(100_000).stream()
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		List<Object> expectedSortedPayload = payload.stream()
				.sorted()
				.collect(Collectors.toList());

		assertEquals(
				postPayloadOutputList(payload),
				expectedSortedPayload
		);
	}

	@Test(priority = 4)
	public void when100KBigIntegersPayloadThenSortPass() {
		List<BigInteger> payload = supplyBigIntegersListOfSize(100_000).stream()
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		List<Object> expectedSortedPayload = payload.stream()
				.sorted()
				.collect(Collectors.toList());

		List<Object> output = postPayloadOutputList(payload);
		List<BigInteger> actual = output
				.stream()
				.map(e -> new BigInteger(e.toString()))
				.collect(Collectors.toList());

		assertEquals(
				actual,
				expectedSortedPayload
		);
	}

	@Test(priority = 4)
	public void when1KBigDecimalPayloadThenSortPass() {
		List<BigDecimal> payload = supplyBigDecimalsListOfSize(1_000).stream()
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		List<Object> expectedSortedPayload = payload.stream()
				.sorted()
				.collect(Collectors.toList());

		List<BigDecimal> actual = postPayloadOutputList(payload)
				.stream()
				.map(e -> new BigDecimal(e.toString()))
				.collect(Collectors.toList());

		assertEquals(
				actual,
				expectedSortedPayload
		);
	}

	@Test(priority = 4)
	public void when1MNumbersPayloadThenSortFail() {
		List<Integer> payload = supplyIntegersListOfSize(1_000_000);
		postPayloadOutputList(payload, HttpStatus.SC_REQUEST_TOO_LONG);
	}
}
