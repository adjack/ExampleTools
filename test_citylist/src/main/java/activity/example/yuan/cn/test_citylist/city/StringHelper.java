package activity.example.yuan.cn.test_citylist.city;

import android.util.Log;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class StringHelper {
	/**
	 * 得到 全拼
	 * 
	 * @param src
	 * @return
	 */
	public static String getPingYin(String src) {

		src = dealDiffCity(src);
		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字
				if (Character.toString(t1[i]).matches(
						"[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 += t2[0];
				} else {
					t4 += Character.toString(t1[i]);
				}
			}
			Log.d("cityChoose1", t4);
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		Log.d("cityChoose1", t4+"-");
		return t4;
	}

	/**
	 * 得到首字�?
	 * 
	 * @param str
	 * @return
	 */
	public static String getHeadChar(String str) {
		String convert = "";
		char word = str.charAt(0);
		String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
		if (pinyinArray != null) {
			convert += pinyinArray[0].charAt(0);
		} else {
			convert += word;
		}
		return convert.toUpperCase();
	}

	/**
	 * 得到中文首字母缩�?
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinYinHeadChar(String str) {
		str = dealDiffCity(str);
		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert.toUpperCase();
	}

	private static String[] specialCharacter = {"长","重"};
	private static String[] specialCharacter_update = {"才","才"};
	private static String dealDiffCity(String src){

		for(int i=0; i < specialCharacter.length; i++){
			if (src.startsWith(specialCharacter[i])) {
				src = src.replace(specialCharacter[i],specialCharacter_update[i]);
				break;
			}
		}
		return src;
	}
}
