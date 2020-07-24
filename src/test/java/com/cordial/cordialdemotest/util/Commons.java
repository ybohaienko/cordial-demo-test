package com.cordial.cordialdemotest.util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

//	public static List<BigInteger> supply() {
//
//
//		BigInteger randomInteger = new BigInteger(1024, new Random());
//
//		System.out.println(randomInteger);
//	}

	List <BigInteger> getList(int numOfElements){
		return IntStream.range(0, numOfElements)
				.mapToObj(e -> new BigInteger(1024, new Random())) // or x -> new Object(x).. or any other constructor
				.collect(Collectors.toList());
	}
}
