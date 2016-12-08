package com.lrb.saas.weixin;

import java.util.HashMap;

public class OpenidSession {

	private static HashMap<String, HashMap<String, Object>> openidMap = new HashMap<>();

	public static void put(String openid, String key, Object value) {
		HashMap<String, Object> hm = openidMap.get(openid);
		if (hm != null) {
		} else {
			hm = new HashMap<>();
			openidMap.put(openid, hm);
		}
		hm.put(key, value);
	}

	public static Object get(String openid, String key) {
		HashMap<String, Object> hm = openidMap.get(openid);
		if (hm == null) {
			return null;
		} else {
			return hm.get(key);
		}
	}

	public static void clear() {
		openidMap.clear();
	}

	public static void remove(String openid, String key) {
		openidMap.get(openid).remove(key);
	}
}
