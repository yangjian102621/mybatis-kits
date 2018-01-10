package com.aizone.mybatis.spring.plus.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author yangjian
 */
public class Misc {
    /** 日志记录器 */
    public static final Logger Logger = LoggerFactory.getLogger(Misc.class);
	/**空字符*/
	public static final String EMPTY = "";
	/**下划线字符*/
	public static final char UNDERLINE = '_';
	/**占位符*/
	public static final String PLACE_HOLDER = "{%s}";
    /** 用来将字节转换成 16 进制表示的字符表 */
    public static final char _hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

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


	/**
	 * 转换为16进制格式的字串
	 *
	 * @param val 32位数值
	 * @return 16进制格式串（如：a1f）
	 */
	public static String toHex(int val) {
		return toHex(val, new StringBuilder(8)).toString();
	}

	/**
	 * 转为 HEX字串
	 *
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

	/**
	 * 64位整数HEX字串，不足16个字符前端补0
	 *
	 * @param val 整数
	 * @return hex格式串
	 */
	public static String toHex64(long val) {
		if (0 == val) {
			return "0000000000000000";
		}
		return toHexFixed(val, new StringBuilder(16)).toString();
	}

	/**
	 * 32位整数HEX字串，不足8个字符前端补0
	 *
	 * @param val 32位数字
	 * @param sb 字串缓冲区，若为null自动创建新的
	 * @return 8字符的HEX编码串
	 */
	public static StringBuilder toHexFixed(int val, StringBuilder sb) {
		if (null == sb) {
			sb = new StringBuilder(8);
		}
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
			sb.append('0');
			sb.append(_hexDigits[(val >> 24) & 0xF]);
			sb.append(_hexDigits[(val >> 20) & 0xF]);
			sb.append(_hexDigits[(val >> 16) & 0xF]);
			sb.append(_hexDigits[(val >> 12) & 0xF]);
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x00100000) {
			sb.append("00");
			sb.append(_hexDigits[(val >> 20) & 0xF]);
			sb.append(_hexDigits[(val >> 16) & 0xF]);
			sb.append(_hexDigits[(val >> 12) & 0xF]);
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x00010000) {
			sb.append("000");
			sb.append(_hexDigits[(val >> 16) & 0xF]);
			sb.append(_hexDigits[(val >> 12) & 0xF]);
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x00001000) {
			sb.append("0000");
			sb.append(_hexDigits[(val >> 12) & 0xF]);
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x00000100) {
			sb.append("00000");
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x00000010) {
			sb.append("000000");
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[(val) & 0xF]);
		} else if (val >= 0x00000001) {
			sb.append("0000000");
			sb.append(_hexDigits[(val) & 0xF]);
		} else {
			sb.append("00000000");
			return sb;
		}
		return sb;
	}

	/**
	 * 64位整数HEX字串，不足16个字符前端补0
	 *
	 * @param val 64位数值
	 * @param sb 字串缓冲区，若为null自动创建新的
	 * @return 16个字符的HEX编码串
	 */
	public static StringBuilder toHexFixed(long val, StringBuilder sb) {
		if (null == sb) {
			sb = new StringBuilder(16);
		}
		// 高32位
		int i32 = (int) ((val >> 32) & 0xFFFFFFFF);
		toHexFixed(i32, sb);
		// 低32位
		i32 = (int) (val & 0xFFFFFFFF);
		toHexFixed(i32, sb);
		return sb;
	}

	/**
	 * 64位整数HEX字串，不足12个字符前端补0
	 * @param val 64位数值
	 * @param sb
	 * @return 12个字符的HEX编码串
	 */
	public static StringBuilder toHexFixed12(long val,StringBuilder sb){
		if (null == sb) {
			sb = new StringBuilder(16);
		}
		// 高32位 只保留4位
		short short4 = (short) ((val >> 32) & 0xFFFF);
		toHexFixed(short4, sb);
		// 低32位
		int i32 = (int) (val & 0xFFFFFFFF);
		toHexFixed(i32, sb);
		return sb;
	}

	/**
	 * 16位整数HEX字串，不足4个字符前端补0
	 *
	 * @param val
	 * @param sb
	 * @return
	 */
	public static StringBuilder toHexFixed(short val, StringBuilder sb) {
		if (null == sb) sb = new StringBuilder(4);

		if (val < 0 || val >= 0x1000) {
			sb.append(_hexDigits[(val >> 12) & 0xF]);
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[val & 0x0F]);
		} else if (val >= 0x0100) {
			sb.append('0');
			sb.append(_hexDigits[(val >> 8) & 0xF]);
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[val & 0x0F]);
		} else if (val >= 0x0010) {
			sb.append("00");
			sb.append(_hexDigits[(val >> 4) & 0xF]);
			sb.append(_hexDigits[val & 0x0F]);
		} else if (val >= 0x0001) {
			sb.append("000");
			sb.append(_hexDigits[val & 0x0F]);
		} else {
			sb.append("0000");
			return sb;
		}
		return sb;
	}

	public static int toInt(String str) {
		if (isEmpty(str)) {
			return 0;
		}
		return Integer.parseInt(str);
	}

    /**
     * 字符串转成数字
     *
     * @param str
     * @param defaultInt 默认值
     * @return
     */
    public static int toInt(String str, int defaultInt) {
        // 如果字串为空时，返回defaultValue
        if (null == str || 0 == str.length()) {
            return defaultInt;
        }

        try {
            char first = str.charAt(0);
            if ('0' == first && str.length() > 2) {
                // 如果是以0x开首表示是十六进制
                first = str.charAt(1);
                if ('x' == first || 'X' == first) {
                    return Integer.parseInt(str.substring(2), 16);
                }
            } else if (str.length() > 1 && ('x' == first || 'X' == first)) {
                // x开首以十六进制
                return Integer.parseInt(str.substring(1), 16);
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        return defaultInt;
    }

}
