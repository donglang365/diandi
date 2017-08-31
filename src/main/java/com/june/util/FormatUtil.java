package com.june.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtil {

	private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

	/**
	 * 去掉所有以<开头以>结尾的标签
	 * @param str
	 * @return
	 */
	public static String filterHtml(String str) {
		Pattern pattern = Pattern.compile(regxpForHtml);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
