package activity.example.yuan.cn.exampletools.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * 网络管理�?
 * 
 */
public class NetworkUtil {
	private static final int CMNET = 0;
	private static final int CMWAP = 1;
	private static final int WIFI = 2;

	/**
	 * 网络是否成功连接
	 */
	public static boolean isOnline(Context context) {
		boolean isOnline = false; 
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo != null) {
			if (networkInfo.isConnected()) { 
				isOnline = true;
			}
		}
		return isOnline;
	}

	/**
	 * 得到设备型号
	 */
	public static String getDeviceModel(Context context) {
		return Build.MODEL.contains(Build.MANUFACTURER) ? Build.MODEL
				: Build.MANUFACTURER + " " + Build.MODEL;
	}

	/**
	 * 返回当前连接网络的状�? CMNET 0 CMWAP 1 WIFI 2
	 * 
	 * @param context
	 * @return
	 */
	public static int getAPNType(Context context) {
		int netType = -1;
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			if ("cmnet".equals(networkInfo.getExtraInfo().toLowerCase())) {
				netType = CMNET;
			} else {
				netType = CMWAP;
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = WIFI;
		}

		return netType;

	}
}