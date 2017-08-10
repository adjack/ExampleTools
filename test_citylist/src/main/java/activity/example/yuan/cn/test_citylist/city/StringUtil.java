package activity.example.yuan.cn.test_citylist.city;

/**
 * 字符串工具类
 */
public class StringUtil {

	/**
	 * 去除字符串中空
	 */
	public static String remove(String resource) {
		StringBuffer buffer = new StringBuffer();
		int position = 0;
		char currentChar;

		while (position < resource.length()) {
			currentChar = resource.charAt(position++);
			if (currentChar != ' ')
				buffer.append(currentChar);
		}
		return buffer.toString();
	}

	public static String deNull(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	public static String Object2String(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}
	
	/**
	 * 功能描述：是否为空白,包括null
	 *
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0 || "null".equals(str);
	}

	public static boolean isNull(Object obj){
		if (obj == null) {
			return true;
		}else{
			if(obj.toString().equals("") || obj.toString().equals("null")){
				return true;
			}
			else{
				return false;
			}
		}
	}

	public static boolean isBlank(Object str) {
		return str == null || str.toString().trim().length() == 0
				|| "null".equals(str);
	}
	
	public static String getKeyValue(Object obj) {
		if (obj == null || obj.toString().trim().length() == 0) {
			return "";
		}
		return obj.toString();
	}

	public static String getKeyIntValue(Object obj) {
		if (obj == null || obj.toString().trim().length() == 0) {
			return 0+"";
		}
		return obj.toString();
	}

	public static String getKeyFloatValue(Object obj) {
		if (obj == null || obj.toString().trim().length() == 0) {
			return "0.0";
		}
		return obj.toString();
	}

}
