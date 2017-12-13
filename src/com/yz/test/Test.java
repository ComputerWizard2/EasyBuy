package com.yz.test;

import java.util.UUID;

public class Test {
	public static void main(String[] args) {
		UUID randomUUID = UUID.randomUUID();
		System.out.println(randomUUID);
		int hashCode = randomUUID.hashCode();
		System.out.println(hashCode);
	}

}
