package org.pack.store.utils;

import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 替换特殊字符 " ' < > & 空格
	 * 还原特殊字符
	 * @param s
	 * @return
	 */
	public static String removeHtmlCh(String s) {
		return s.replaceAll("&quot;", "\"").replaceAll("&apos;", "'")
				.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
				.replaceAll("&amp;", "&").replaceAll("&nbsp;", " ")
				.replaceAll("&ldquo", "").replaceAll("&rdquo", "");//“”
	}


	/**
	 * 保留有限特殊字符 ( ) , : -
	 * 1、左括号和右括号统一
	 * 2、逗号统一
	 * 3、冒号统一
	 * 4、横杠统一
	 * 5、需要被干掉的符号
	 * @param s
	 * @return
	 */
	public static String transCH(String s) {
		String res = s;
		res = res.replaceAll("[\\[（｛【《\\{]", "(");
		res = res.replaceAll("[\\]）｝】》\\}]", ")");
		//res = res.replaceAll("[，、；‘’　。·;]", ",");
		res = res.replaceAll("[，；‘’　。·;]", ",");
		//res = res.replaceAll("[，、‘’　。·]", ",");
		//res = res.replaceAll("[；;]", ";");
		res = res.replaceAll("[：]", ":");
		res = res.replaceAll("[—－～→~]+", "-"); // 去除特殊字符
		res = res.replaceAll("[◇◆★●\"#“”\\+āáǎàōóǒòêēéěèīíǐìūúǔù]", "");// 出去这些符号
		
		return res;
	}


	
	/**
	 * 判断是否存在中文字符（仅限派送区划解析使用）
	 * @param str
	 * @return
	 */
	public static boolean isNoChinese(String str) {
		if (str == null) {
			return false;
		}
		str = str.replaceAll("[(),:;\\-_、 ]", "");
		int length = str.length();
		if (length == 0) {
			return false;
		}
		
		int i = 0;
		
		for (; i < length; i++) {
			char c = str.charAt(i);
			// 先判断数字 再判断小写 最后判断大写
			if ( !( 
					(c >= 0x0030 && c <= 0x0039) || (c >= 0x0061 && c <= 0x007A) || (c >= 0x0041 && c <= 0x005A) 
				  ) 
			   ) {
				return false;
			}
		}
		return true;
	}
	
	public static int toInt(String s) {
		int r = 0;
		try {
			r = Integer.parseInt(s);
		} catch (Exception e) {
		}
		return r;
	}
	
	//用正则表达式,判断字符串是否为数字gdd
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 } 
	
	//验证字符串是否为空
	public static boolean isNullStr(String str){
		return str == null || str.trim().length() == 0 || "null".equals(str);
	}
	
	public static boolean isNotNullStr(String str){
		return str != null && str.trim().length() > 0;
	}
	
	
}
