package com.cordial.cordialdemotest.util;

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
}
