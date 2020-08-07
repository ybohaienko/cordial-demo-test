package com.sorting.sortingdemotest.sorting;

import com.sorting.sortingdemotest.RestApiTestStarter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.sorting.sortingdemotest.util.Commons.*;
import static org.testng.Assert.assertEquals;

@Test(groups = "func")
public class FunctionalTest extends RestApiTestStarter {
	@Test(priority = 1, dataProvider = "notOrderedDp", dependsOnGroups = "smoke")
	public void whenRandomOrderedThenSortedOutput(Object[] payload) {
		List<Object> actual = postPayloadOutputList(Arrays.asList(payload));
		List<Object> expected = Arrays.stream(payload)
				.sorted()
				.collect(Collectors.toList());

		assertEquals(actual, expected);
	}

	@DataProvider
	public Object[][] notOrderedDp() {
		return new Object[][]{
				{new Object[]{2, 3, 1}},
				{new Object[]{3, 2, 1}},
				{new Object[]{1, 2, 3}},
				{new Object[]{0, 1, 1_000}},
//				TODO: fix bug (QA-1) and uncomment
				{new Object[]{1, -1, 0}},
				{new Object[]{-2, -1, -1_000}},
		};
	}

	@Test(priority = 2, dataProvider = "notOrderedFloatsDp")
	public void whenRandomOrderFloatsThenSortedOutput(Object[] payload) {
		List<Object> actual = postPayloadOutputList(Arrays.asList(payload));
		List<Object> expected = Arrays.stream(payload)
				.sorted()
				.collect(Collectors.toList());
		assertEquals(actual, expected);
	}

	@DataProvider
	public Object[][] notOrderedFloatsDp() {
		return new Object[][]{
				{new Object[]{0.1f, 0.3f, 0.2f}},
				{new Object[]{0.1f, 0.01f, 0.001f}},
				{new Object[]{1.99999f, 1.9999f, 1.999f}},
				{new Object[]{0.001f, 1.001f, 1_000.001f}},
//				TODO: fix bug (QA-1) and uncomment
				{new Object[]{0.0f, 0.1f, -0.1f}},
				{new Object[]{-1.1f, -0.1f, -2.1f}},
				{new Object[]{-10.1f, -1.1f, -1_000.1f}},
		};
	}

	@Test(priority = 3)
	public void whenFloatsWithZeroThenIntegerOutput() {
		Object[] payload = new Object[]{0.0f, 1.0f, 1000.0f};
		List<Object> actual = postPayloadOutputList(Arrays.asList(payload));
		List<Object> expected = Arrays.asList(new Object[]{0, 1, 1000});
		assertEquals(actual, expected);
	}

	@Test(priority = 3)
	public void whenLongFloatsThenExponentialFloatOutput() {
		Object[] payload = new Object[]{0.00000000001, 0.000000000001, 0.0000000000001};
		List<Object> actual = postPayloadOutputList(Arrays.asList(payload));
		List<Object> expected = Arrays.stream(payload)
				.sorted()
				.map(e -> e = Float.parseFloat(e.toString()))
				.collect(Collectors.toList());
		assertEquals(actual, expected);
	}

	@Test(priority = 3)
	public void whenBigIntegersThenSortedOutput() {
		List<BigInteger> payload = supplyBigIntegersListOfSize(3).stream()
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		List<Object> expectedSortedPayload = payload.stream()
				.sorted()
				.collect(Collectors.toList());

		List<BigInteger> actual = postPayloadOutputList(payload)
				.stream()
				.map(e -> new BigInteger(e.toString()))
				.collect(Collectors.toList());
		assertEquals(actual, expectedSortedPayload);
	}

	@Test(priority = 3)
	public void whenBigDecimalsPayloadThenSortPass() {
		List<BigDecimal> payload = supplyBigDecimalsListOfSize(3).stream()
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		List<Object> expectedSortedPayload = payload.stream()
				.sorted()
				.collect(Collectors.toList());

		List<BigDecimal> actual = postPayloadOutputList(payload)
				.stream()
				.map(e -> new BigDecimal(e.toString()))
				.collect(Collectors.toList());
		assertEquals(actual, expectedSortedPayload);
	}

	@Test(priority = 4)
	public void whenOneInDataSetThenEmptyOutput() {
		List<Integer> payload = supplyIntegersListOfSize(1);
		int expectedSize = 0;

		assertEquals(
				postPayloadOutputList(payload).size(),
				expectedSize
		);
	}

	@Test(priority = 4)
	public void whenOEmptyDataSetThenEmptyOutput() {
		List<Integer> payload = new ArrayList<>();
		int expectedSize = 0;

		assertEquals(
				postPayloadOutputList(payload).size(),
				expectedSize
		);
	}

	@Test(priority = 4, dataProvider = "dataTypesDp")
	public void whenDiffDataTypesThenTheSame(Object[] payload, Object[] expectedData) {
		List<Object> actual = postPayloadOutputList(Arrays.asList(payload));
		List<Object> expected = Arrays.asList(expectedData);
		assertEquals(actual, expected);
	}

	@DataProvider
	public Object[][] dataTypesDp() {
		return new Object[][]{
				{new Object[]{true, false, true}, new Object[]{false, true, true}},
				{new Object[]{"c", "a", "b"}, new Object[]{"c", "a", "b"}},
				{new Object[]{null, 0, false}, new Object[]{null, 0, false}},
				{new Object[]{new ArrayList<>(), 0, new ArrayList<>()}, new Object[]{0, new ArrayList<>(), new ArrayList<>()}},
		};
	}
}
