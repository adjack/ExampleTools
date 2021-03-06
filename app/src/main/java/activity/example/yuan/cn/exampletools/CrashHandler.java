package activity.example.yuan.cn.exampletools;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Looper;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * UncaughtExceptionHandler：线程未捕获异常控制器是用来处理未捕获异常的 如果程序出现了未捕获异常默认情况下则会出现强行关闭对话框
 * 实现该接口并注册为程序中的默认未捕获异常处理 这样当未捕获异常发生时，就可以做些异常处理操 例如：收集异常信息，发送错误报告等
 * 
 * UncaughtException处理当程序发生Uncaught异常的时由该类来接管程序,并记录发送错误报告
 */
public class CrashHandler implements UncaughtExceptionHandler {
	/** Debug Log Tag */
	public static final String TAG = "CrashHandler";

	/** CrashHandler实例 */
	private static CrashHandler INSTANCE;

	/** 获取CrashHandler实例 ,单例模式 */
	public static CrashHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new CrashHandler();
		return INSTANCE;
	}

	/** 程序的Context对象 */
	private Context mContext;

	// 系统默认的UncaughtException处理
	private UncaughtExceptionHandler mDefaultHandler;

	/** 用来存储设备信息和异常信�? */
	private Map<String, String> info = new HashMap<String, String>();

	/** 用于格式化日�?,作为日志文件名的�?部分 */
	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");

	/**
	 * 初始�?,注册Context对象, 获取系统默认的UncaughtException处理�?, 设置该CrashHandler为程序的默认处理�?
	 * 
	 * @param ctx
	 */
	public void init(Context ctx) {
		mContext = ctx;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// config = SharedPreferencesConfig.config(ctx);
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			// 如果用户没有处理则让系统默认的异常处理器来处
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			Log.d(TAG, "......"+ex.toString());
			Intent intent = new Intent();
			intent.setClass(mContext, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mContext.startActivity(intent);
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成
	 * 
	 * @param ex
	 * @return true:如果处理了该异常信息;否则返回false
	 */
	private boolean handleException(final Throwable ex) {
		if (ex == null) {
			return true;
		}
		// 使用Toast来显示异常信�?
		new Thread() {
			@Override
			public void run() {
				// Toast 显示�?要出现在�?个线程的消息队列�?
				Looper.prepare();
				// "很抱歉程序出现异常！",Toast.LENGTH_LONG).show();
//				sendServer(ex);//保存到服务器
//				saveCrashInfo2File(ex);
				
				Looper.loop();
			}
		}.start();
		// 保存错误报告文件
		 saveCrashInfo2File(ex);
		// sendEmail();

		return true;
	}

	private void sendServer(Throwable ex) {
//		Log.d(TAG, "return:"+getErrorInfo(ex));
//		if (NetworkUtil.isOnline(mContext)) {			
//			try {
//				String info = getErrorInfo(ex);
//				Log.d(TAG, info);
//				Map<String, Object> params = new HashMap<String, Object>();
//				params.put("data", info);
//				DE.serverCall("otherinterface/error_report", params, null);
//				Log.d(TAG, "ok");
//			} catch (Exception e) {
//			}
//		}

	}

	/**
	 * 得到错误的相关信
	 * @param ex
	 * @return
	 */
	private String getErrorInfo(Throwable ex) {
		StringBuilder strbuilder = new StringBuilder();
		try {
			// 获取packagemanager的实�?
			PackageManager packageManager = mContext.getPackageManager();
			// getPackageName()是你当前类的包名�?0代表是获取版本信�?
			PackageInfo packInfo;
			packInfo = packageManager.getPackageInfo(mContext.getPackageName(),0);
			strbuilder.append("\n用户位置:"+"\n");
			strbuilder.append("应用版本:"+packInfo.versionName+"\n");
			strbuilder.append("手机型号"+android.os.Build.MODEL+"\n");
			strbuilder.append("SDK版本?"+android.os.Build.VERSION.SDK_INT+"\n");
			strbuilder.append("系统版本?"+android.os.Build.VERSION.RELEASE+"\n");
			String temp = "";
			try {
				if(ex != null){
					Writer writer = new StringWriter();
					PrintWriter pw = new PrintWriter(writer);
					ex.printStackTrace(pw);
					Throwable cause = ex.getCause();

					while (cause != null) {
						cause.printStackTrace(pw);
						cause = cause.getCause();
					}
					pw.close();// 记得关闭
					temp = writer.toString();					
				}
			} catch (Exception e) {
			}
			strbuilder.append("错误信息:" + temp);
					
			
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return strbuilder.toString();

	}

//	private void sendEmail() {
		/*
		 * try {
		 * 
		 * MailSenderInfo mailInfo = new MailSenderInfo();
		 * mailInfo.setMailServerHost("smtp.qq.com");
		 * mailInfo.setMailServerPort("25"); mailInfo.setValidate(true);
		 * mailInfo.setUserName("1649462224@qq.com"); //你的邮箱地址
		 * mailInfo.setPassword("jhjhj");//您的邮箱密码
		 * mailInfo.setFromAddress("1649462224@qq.com");
		 * mailInfo.setToAddress("916000874@qq.com");
		 * mailInfo.setSubject("测试bug"); mailInfo.setContent(
		 * "error:1111111121121\nmskamskamsk\nsjaksakmsk\nsajksaksk"); //
		 * 这个类主要来发�?�邮�? SimpleMailSender sms = new SimpleMailSender();
		 * sms.sendTextMail(mailInfo);// 发�?�文体格�? //
		 * sms.sendHtmlMail(mailInfo);//发�?�html格式
		 * 
		 * } catch (Exception e) { Log.e("SendMail", e.getMessage(), e); }
		 */
//	}

	private String saveCrashInfo2File(Throwable ex) {
		Log.d(TAG, "......"+ex.toString());
//		StringBuffer sb = new StringBuffer();
//		for (Map.Entry<String, String> entry : info.entrySet()) {
//			String key = entry.getKey();
//			String value = entry.getValue();
//			sb.append(key + "=" + value + "\r\n");
//		}
//		Writer writer = new StringWriter();
//		PrintWriter pw = new PrintWriter(writer);
//		ex.printStackTrace(pw);
//		Throwable cause = ex.getCause();
//		// 循环�?把所有的异常信息写入writer�?
//		while (cause != null) {
//			cause.printStackTrace(pw);
//			cause = cause.getCause();
//		}
//		pw.close();// 记得关闭
//		String result = writer.toString();
//		sb.append(result);
//		// 保存文件
//		long timetamp = System.currentTimeMillis();
//		String time = format.format(new Date());
//		String fileName = "crash-" + time + "-" + timetamp + ".log";
//		if (Environment.getExternalStorageState().equals(
//				Environment.MEDIA_MOUNTED)) {
//			try {
//				File dir = new File(Environment.getExternalStorageDirectory()
//						.getAbsolutePath() + File.separator + "tcgj_rash");
//				Log.d(TAG, dir.toString());
//				if (!dir.exists())
//					dir.mkdir();
//				FileOutputStream fos = new FileOutputStream(new File(dir,
//						fileName));
//				fos.write(sb.toString().getBytes());
//				fos.close();
//				return fileName;
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		return null;
	}
}
