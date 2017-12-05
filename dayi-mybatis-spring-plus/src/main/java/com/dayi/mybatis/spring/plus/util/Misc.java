package com.dayi.mybatis.spring.plus.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author chenzhaoju
 */
public class Misc {

	/**
	 * 空字符
	 */
	public static final String EMPTY = "";

	/**
	 * 下划线字符
	 */
	public static final char UNDERLINE = '_';

	/**
	 * 占位符
	 */
	public static final String PLACE_HOLDER = "{%s}";

	/**
	 * <p>
	 * 判断字符串是否为空
	 * </p>
	 *
	 * @param cs
	 *            需要判断字符串
	 * @return 判断结果
	 */
	public static boolean isEmpty(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * 字符串驼峰转下划线格式
	 * </p>
	 *
	 * @param param
	 *            需要转换的字符串
	 * @return 转换好的字符串
	 */
	public static String toCamelUnderline(String param) {
		if (isEmpty(param)) {
			return EMPTY;
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c) && i > 0) {
				sb.append(UNDERLINE);
			}
			sb.append(Character.toLowerCase(c));
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 字符串下划线转驼峰格式
	 * </p>
	 *
	 * @param param
	 *            需要转换的字符串
	 * @return 转换好的字符串
	 */
	public static String underlineToCamel(String param) {
		if (isEmpty(param)) {
			return EMPTY;
		}
		String temp = param.toLowerCase();
		int len = temp.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = temp.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(temp.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 首字母转换小写
	 * </p>
	 *
	 * @param param
	 *            需要转换的字符串
	 * @return 转换好的字符串
	 */
	public static String firstToLowerCase(String param) {
		if (isEmpty(param)) {
			return EMPTY;
		}
		StringBuilder sb = new StringBuilder(param.length());
		sb.append(param.substring(0, 1).toLowerCase());
		sb.append(param.substring(1));
		return sb.toString();
	}

	/**
	 * <p>
	 * 判断字符串是否为纯大写字母
	 * </p>
	 *
	 * @param str
	 *            要匹配的字符串
	 * @return
	 */
	public static boolean isUpperCase(String str) {
		return match("^[A-Z]+$", str);
	}

	/**
	 * <p>
	 * 正则表达式匹配
	 * </p>
	 *
	 * @param regex
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * <p>
	 * 拼接字符串第二个字符串第一个字母大写
	 * </p>
	 *
	 * @param concatStr
	 * @param str
	 * @return
	 */
	public static String concatCapitalize(String concatStr, final String str) {
		if (isEmpty(concatStr)) {
			concatStr = EMPTY;
		}
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}

		final char firstChar = str.charAt(0);
		if (Character.isTitleCase(firstChar)) {
			// already capitalized
			return str;
		}

		StringBuilder sb = new StringBuilder(strLen);
		sb.append(concatStr);
		sb.append(Character.toTitleCase(firstChar));
		sb.append(str.substring(1));
		return sb.toString();
	}

	/**
	 * <p>
	 * 字符串第一个字母大写
	 * </p>
	 *
	 * @param str
	 * @return
	 */
	public static String capitalize(final String str) {
		return concatCapitalize(null, str);
	}


}
