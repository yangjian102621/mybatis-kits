package org.rockyang.mybatis.plus.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author chenzhaoju
 */
public class Misc {
	/**空字符*/
	public static final String EMPTY = "";
	/**下划线字符*/
	public static final char UNDERLINE = '_';
	/** 用来将字节转换成 16 进制表示的字符表 */
    public static final char _hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	/**
	 * 判断字符串是否为空
	 * @param cs 需要判断字符串
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
	 * 字符串驼峰转下划线格式
	 * @param param 需要转换的字符串
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
	 * 字符串下划线转驼峰格式
	 * @param param 需要转换的字符串
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
	 * 首字母转换小写
	 * @param param 需要转换的字符串
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
	 * 判断字符串是否为纯大写字母
	 * @param str 要匹配的字符串
	 * @return 返回判断结果
	 */
	public static boolean isUpperCase(String str) {
		return match("^[A-Z]+$", str);
	}

	/**
	 * 正则表达式匹配
	 * @param regex 正则表达式字符串
	 * @param str 要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/* 拼接字符串第二个字符串第一个字母大写 */
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

	/* 字符串第一个字母大写 */
	public static String capitalize(final String str) {
		return concatCapitalize(null, str);
	}


	/**
	 * 转换为16进制格式的字串
	 * @param val 32位数值
	 * @return 16进制格式串（如：a1f）
	 */
	public static String toHex(int val) {
		return toHex(val, new StringBuilder(8)).toString();
	}

	/**
	 * 转为 HEX字串
	 * @param val 32位数值
	 * @param sb 转换HEX后的追加字串缓冲区
	 * @return 追加后的字串缓冲区
	 */
	public static StringBuilder toHex(int val, StringBuilder sb) {
		if (val < 0 || val >= 0x10000000) {
			sb.append(_hexDigits[(val >> 28) & 0xF]);
			sb.append(_hexDigits[(val >> 24) & 0xF]);
			sb.append(_hexDigits[(val >> 20) & 0xF]);
			sb.append(_hexDigits[(val >> 16) & 0xF]);
			sb.append(_hexDigits[(val >> 12) & 0xF]);
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x01000000) {
			sb.append(_hexDigits[(val >> 24) & 0xF]);
			sb.append(_hexDigits[(val >> 20) & 0xF]);
			sb.append(_hexDigits[(val >> 16) & 0xF]);
			sb.append(_hexDigits[(val >> 12) & 0xF]);
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x00100000) {
			sb.append(_hexDigits[(val >> 20) & 0xF]);
			sb.append(_hexDigits[(val >> 16) & 0xF]);
			sb.append(_hexDigits[(val >> 12) & 0xF]);
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x00010000) {
			sb.append(_hexDigits[(val >> 16) & 0xF]);
			sb.append(_hexDigits[(val >> 12) & 0xF]);
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x00001000) {
			sb.append(_hexDigits[(val >> 12) & 0xF]);
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x00000100) {
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x00000010) {
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x00000001) {
			sb.append(_hexDigits[(val) & 0xF]);
		} else {
			sb.append("0");
			return sb;
		}
		return sb;
	}

}
