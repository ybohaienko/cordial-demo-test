package com.cordial.cordialdemotest.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Commons {
	public static List<Integer> supplyIntegersListOfSize(int size) {
		int[] ints = IntStream.generate(()
				-> new Random()
				.nextInt(size))
				.limit(size)
				.toArray();
		return Arrays
				.stream(ints)
				.boxed()
				.collect(Collectors.toList());
	}

	public static List<BigInteger> supplyBigIntegersListOfSize(int size) {
		return IntStream.range(0, size)
				.mapToObj(e -> new BigInteger(1024, new Random()))
				.collect(Collectors.toList());
	}

	public static List<BigDecimal> supplyBigDecimalsListOfSize(int size) {
		return IntStream.range(0, size)
				.mapToObj(e -> new BigDecimal(new BigInteger(1024, new Random()), 512))
				.collect(Collectors.toList());
	}
}
