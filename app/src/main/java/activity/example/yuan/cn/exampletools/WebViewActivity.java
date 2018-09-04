package activity.example.yuan.cn.exampletools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import activity.example.yuan.cn.exampletools.utils.ImageUtil;
import activity.example.yuan.cn.exampletools.utils.NetworkUtil;
import activity.example.yuan.cn.exampletools.view.ProgressWebView;

/**
 * Created by 123 on 2018/4/20.
 */

public class WebViewActivity extends Activity{
    public ProgressWebView webView;
    String loadurl = "https://mb.wyjr168.com/Ad/vip.html?recode=9153670&userName=WY3WYVXZ&sign=23c16edf8dae0309193cf0b460c1aed3&timeStamp=20180508091727&systemType=1";
    String homeurl = "https://mb.wyjr168.com/Ad/vip.html?recode=9153670&userName=WY3WYVXZ&sign=23c16edf8dae0309193cf0b460c1aed3&timeStamp=20180508091727&systemType=1";//主页

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Integer.valueOf("dsdkakd");

        webView = findViewById(R.id.webview);
        //判断显示
        if (!NetworkUtil.isOnline(this)) {//无网络
            webView.setVisibility(View.GONE);
        } else {
            webView.setVisibility(View.VISIBLE);
        }

        initWebView();
        webView.loadUrl(loadurl,AddRerer(homeurl));
        webView.setWebViewClient(new MyWebViewClient());
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("shouldOverrid",url);
            Map<String, String> extraHeaders = AddRerer(view.getUrl());
            if(url.contains("tel:")){
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                    startActivity(intent);
                }
                catch (SecurityException ex){}

            }
            else if(url.contains("mailto:")){
                Intent data=new Intent(Intent.ACTION_SENDTO);
                data.setData(Uri.parse(url));
                data.putExtra(Intent.EXTRA_SUBJECT, "");
                data.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(data);
            }
            else{
                view.loadUrl(url,extraHeaders);
            }
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            Log.d("onReceive_log", "onReceivedError"+failingUrl);
            webView.setVisibility(View.GONE);
//			super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url,
                                           boolean isReload) {
            Log.d("onReceive_log", "doUpdateVisitedHistory");
            super.doUpdateVisitedHistory(view, url, isReload);
        }

        @Override
        public void onFormResubmission(WebView view, Message dontResend,
                                       Message resend) {
            Log.d("onReceive_log", "onFormResubmission");
            super.onFormResubmission(view, dontResend, resend);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            Log.d("onReceive_log", "onLoadResource");
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d("onReceive_log", "onPageFinished");
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d("onReceive_log", "onPageStarted");
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedHttpAuthRequest(WebView view,
                                              HttpAuthHandler handler, String host, String realm) {
            Log.d("onReceive_log", "onReceivedHttpAuthRequest");
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }

        @Override
        public void onReceivedLoginRequest(WebView view, String realm,
                                           String account, String args) {
            Log.d("onReceive_log", "onReceivedLoginRequest");
            super.onReceivedLoginRequest(view, realm, account, args);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                       SslError error) {
            Log.d("onReceive_log", "onReceivedSslError");
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            Log.d("onReceive_log", "onScaleChanged");
            super.onScaleChanged(view, oldScale, newScale);
        }

        @Override
        public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
            Log.d("onReceive_log", "onUnhandledKeyEvent");
            super.onUnhandledKeyEvent(view, event);
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view,
                                                          String url) {
            Log.d("onReceive_log", "WebResourceResponse");
            return super.shouldInterceptRequest(view, url);
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            Log.d("onReceive_log", "shouldOverrideKeyEvent");
            return super.shouldOverrideKeyEvent(view, event);
        }
    }

    private boolean ifMainPage = true;// 是否是主页
    private Map<String,String> AddRerer(String url){
        Map<String, String> extraHeaders = new HashMap<String, String>();
        if(url != null)extraHeaders.put("Referer", url);
        return extraHeaders;
    }

    private Intent mSourceIntent;
    private static final int REQUEST_CODE_PICK_IMAGE = 0;
    private static final int REQUEST_CODE_IMAGE_CAPTURE = 1;
    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CODE_IMAGE_CAPTURE:
            case REQUEST_CODE_PICK_IMAGE: {
                try {
                    if (mUploadMessage == null) {
                        return;
                    }
                    String sourcePath = ImageUtil.retrievePath(this, mSourceIntent, data);
                    if (TextUtils.isEmpty(sourcePath) || !new File(sourcePath).exists()) {
                        Log.w("mUploadMessage", "sourcePath empty or not exists.");
                        break;
                    }
                    Uri uri = Uri.fromFile(new File(sourcePath));
                    mUploadMessage.onReceiveValue(uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

    @SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
    private void initWebView() {

        // 设置允许加载JS
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.requestFocus(); // WebView显示焦点

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); // 是否支持JavaScript
        settings.setBuiltInZoomControls(false); // 设置是否支持缩放
        settings.setSupportZoom(true); // 设置是否支持变焦
        settings.setCacheMode(WebSettings.LOAD_DEFAULT); // 设置缓存的模式

        settings.setAllowFileAccess(true);

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.d("onReceive_log", "onProgressChanged");
                if (newProgress == 100) {
                    webView.getProgressBar().setVisibility(View.GONE);
                } else {
                    if (webView.getProgressBar().getVisibility() == View.GONE)
                        webView.getProgressBar().setVisibility(View.VISIBLE);
                    webView.getProgressBar().setProgress(newProgress);
                }
                Log.d("onProgressChanged", newProgress + "-");
                super.onProgressChanged(view, newProgress);
            }


//			//For Android 3.0+
//		    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
//		    	openFileChoo(uploadMsg, acceptType);
//		    }
//
//		    // For Android < 3.0
//		    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
//		    	openFileChoo(uploadMsg, "");
//		    }

            // For Android >4.1
            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                        String acceptType, String capture) {
                Log.d("onReceive_log", "openFileChooser");

//				Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//				i.addCategory(Intent.CATEGORY_OPENABLE);
//				i.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI,
//						"image/*");
//				startActivityForResult(i, FILECHOOSER_RESULTCODE);

                openFileChoo(uploadMsg, acceptType);


            }

            @SuppressLint("NewApi")
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("onReceive_log", webView.getUrl()+"-------"+loadurl);

                if (webView.getUrl().contains(loadurl)) {
                    ifMainPage = true;
                } else {
                    ifMainPage = false;
                }
                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog,
                                          boolean isUserGesture, Message resultMsg) {
                Log.d("onReceive_log", "onCreateWindow");
                return super.onCreateWindow(view, isDialog, isUserGesture,
                        resultMsg);
            }

            @Override
            public void onGeolocationPermissionsHidePrompt() {
                Log.d("onReceive_log", "onGeolocationPermissionsHidePrompt");
                super.onGeolocationPermissionsHidePrompt();
            }

            @SuppressLint("NewApi")
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissions.Callback callback) {
                Log.d("onReceive_log", "onGeolocationPermissionsShowPrompt");
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            @Override
            public void onHideCustomView() {
                Log.d("onReceive_log", "onHideCustomView");
                super.onHideCustomView();
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     final JsResult result) {
                Log.d("onReceive_log", "onJsAlert");
                final AlertDialog.Builder builder = new AlertDialog.Builder(
                        view.getContext());
                builder.setMessage(message).setPositiveButton("确定", null);

                // 不需要绑定按键事件
                // 屏蔽keycode等于84之类的按键
                builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    public boolean onKey(DialogInterface dialog, int keyCode,
                                         KeyEvent event) {
                        Log.v("onJsAlert", "keyCode==" + keyCode + "event="
                                + event);
                        return true;
                    }
                });
                // 禁止响应按back键的事件
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
                result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。

                return true;
            }

            @Override
            public boolean onJsBeforeUnload(WebView view, String url,
                                            String message, JsResult result) {
                Log.d("onReceive_log", "onJsBeforeUnload");
                return super.onJsBeforeUnload(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url,
                                       String message, final JsResult result) {
                // Log.d("acceptType", "onJsConfirm");
                final AlertDialog.Builder builder = new AlertDialog.Builder(
                        view.getContext());
                builder.setMessage(message)
                        .setPositiveButton(
                                "确定",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface arg0,
                                                        int arg1) {
                                        result.confirm();
                                    }

                                })
                        .setNegativeButton(
                                "取消",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface arg0,
                                                        int arg1) {
                                        result.cancel();
                                    }

                                });
                builder.setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        result.cancel();
                    }
                });

                // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
                builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode,
                                         KeyEvent event) {
                        Log.v("onJsConfirm", "keyCode==" + keyCode + "event="
                                + event);
                        return true;
                    }
                });
                // 禁止响应按back键的事件
                // builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
                // return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message,
                                      String defaultValue, final JsPromptResult result) {
                Log.d("onReceive_log", "onJsPrompt");
                //
                final AlertDialog.Builder builder = new AlertDialog.Builder(
                        view.getContext());

                builder.setMessage(message);

                final EditText et = new EditText(view.getContext());
                et.setSingleLine();
                et.setText(defaultValue);
                builder.setView(et)
                        .setPositiveButton(
                                "确定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        result.confirm(et.getText().toString());
                                    }

                                })
                        .setNeutralButton(
                                "取消",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        result.cancel();
                                    }
                                });

                // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
                builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    public boolean onKey(DialogInterface dialog, int keyCode,
                                         KeyEvent event) {
                        Log.v("onJsPrompt", "keyCode==" + keyCode + "event="
                                + event);
                        return true;
                    }
                });

                // 禁止响应按back键的事件
                // builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
                // return super.onJsPrompt(view, url, message,
                // defaultValue,result);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                Log.d("onReceive_log", "onReceivedIcon");
                super.onReceivedIcon(view, icon);
            }

            @SuppressLint("NewApi")
            @Override
            public void onReceivedTitle(WebView view, String title) {
                Log.d("onReceive_log", "onReceivedTitle");
                super.onReceivedTitle(view, title);
            }

            @SuppressLint("NewApi")
            @Override
            public void onReceivedTouchIconUrl(WebView view, String url,
                                               boolean precomposed) {
                Log.d("onReceive_log", "onReceivedTouchIconUrl");
                super.onReceivedTouchIconUrl(view, url, precomposed);
            }

            @Override
            public void onRequestFocus(WebView view) {
                Log.d("onReceive_log", "onRequestFocus");
                super.onRequestFocus(view);
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                Log.d("onReceive_log", "onShowCustomView");
                super.onShowCustomView(view, callback);
            }

            @Override
            public Bitmap getDefaultVideoPoster() {
                Log.d("onReceive_log", "getDefaultVideoPoster");
                return super.getDefaultVideoPoster();
            }

            @Override
            public View getVideoLoadingProgressView() {
                Log.d("onReceive_log", "getVideoLoadingProgressView");
                return super.getVideoLoadingProgressView();
            }

            @Override
            public void getVisitedHistory(ValueCallback<String[]> callback) {
                Log.d("onReceive_log", "getVisitedHistory");
                super.getVisitedHistory(callback);
            }

            @Override
            public void onCloseWindow(WebView window) {
                Log.d("onReceive_log", "onCloseWindow");
                super.onCloseWindow(window);
            }

            @Override
            @Deprecated
            public void onConsoleMessage(String message, int lineNumber,
                                         String sourceID) {
                Log.d("onReceive_log", "onConsoleMessage");
                super.onConsoleMessage(message, lineNumber, sourceID);
            }

            @Override
            @Deprecated
            public void onExceededDatabaseQuota(String url,
                                                String databaseIdentifier, long quota,
                                                long estimatedDatabaseSize, long totalQuota,
                                                WebStorage.QuotaUpdater quotaUpdater) {
                Log.d("onReceive_log", "onExceededDatabaseQuota");
                super.onExceededDatabaseQuota(url, databaseIdentifier, quota,
                        estimatedDatabaseSize, totalQuota, quotaUpdater);
            }

            @Override
            @Deprecated
            public boolean onJsTimeout() {
                Log.d("onReceive_log", "onJsTimeout");
                return super.onJsTimeout();
            }

            @Override
            @Deprecated
            public void onReachedMaxAppCacheSize(long requiredStorage,
                                                 long quota, WebStorage.QuotaUpdater quotaUpdater) {
                Log.d("onReceive_log", "onReachedMaxAppCacheSize");
                super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
            }

            @Override
            @Deprecated
            public void onShowCustomView(View view, int requestedOrientation,
                                         CustomViewCallback callback) {
                Log.d("onReceive_log", "onShowCustomView");
                super.onShowCustomView(view, requestedOrientation, callback);
            }



        });
    }

    private ValueCallback<Uri> mUploadMessage;
    public void openFileChoo(ValueCallback<Uri> uploadMsg, String acceptType) {
        mUploadMessage = uploadMsg;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("操作");
        alertDialog.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface arg0) {
                if (mUploadMessage != null) {
                    mUploadMessage.onReceiveValue(null);
                    mUploadMessage = null;
                }

            }
        });
        alertDialog.setItems(R.array.imgs_options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            mSourceIntent = ImageUtil.choosePicture();
                            startActivityForResult(mSourceIntent, REQUEST_CODE_PICK_IMAGE);
                        } else {
                            mSourceIntent = ImageUtil.takeBigPicture();
                            startActivityForResult(mSourceIntent, REQUEST_CODE_IMAGE_CAPTURE);
                        }
                    }
                }
        );
        try {
            alertDialog.show();
        } catch (Exception e) {
            Log.d("alertDialog", e.toString());
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            if(webView != null){
                webView.goBack();// 返回前一个页面
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
