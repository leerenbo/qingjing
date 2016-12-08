package com.lrb.saas.core.util;

import java.util.UUID;

public class UUIDUtil {
	public static String randomUUIDString() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
