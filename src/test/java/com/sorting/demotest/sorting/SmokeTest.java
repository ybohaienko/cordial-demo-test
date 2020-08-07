package com.sorting.demotest.sorting;

import com.sorting.demotest.RestApiTestStarter;
import com.sorting.demotest.util.Commons;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

@Test(groups = "smoke")
public class SmokeTest extends RestApiTestStarter {

	@Test(priority = 1)
	public void whenPayloadThenSortedOutput() {
		List<Integer> payload = Commons.supplyIntegersListOfSize(3);
		List<Object> expectedSortedPayload = payload.stream()
				.sorted()
				.collect(Collectors.toList());

		assertEquals(
				postPayloadOutputList(payload),
				expectedSortedPayload
		);
	}

	@Test(priority = 2)
	public void whenPayloadThenSameSizeOutput() {
		List<Integer> payload = Commons.supplyIntegersListOfSize(3);
		int expectedSize = payload.size();

		assertEquals(
				postPayloadOutputList(payload).size(),
				expectedSize
		);
	}
}
