package activity.example.yuan.cn.test_webview;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {

    private WebView contentWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        contentWebView = (WebView) findViewById(R.id.webview);

        //自适应屏幕
//        contentWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        contentWebView.getSettings().setLoadWithOverviewMode(true);
        // 启用javascript
        contentWebView.setVerticalScrollBarEnabled(false);
        contentWebView.setHorizontalScrollBarEnabled(false);
        contentWebView.requestFocus(); // WebView显示焦点

        WebSettings settings = contentWebView.getSettings();
        settings.setJavaScriptEnabled(true); // 是否支持JavaScript
        settings.setBuiltInZoomControls(false); // 设置是否支持缩放
        settings.setSupportZoom(true); // 设置是否支持变焦
        settings.setCacheMode(WebSettings.LOAD_DEFAULT); // 设置缓存的模式

        settings.setAllowFileAccess(true);
        // 从assets目录下面的加载html
        contentWebView.loadUrl("file:///android_asset/webtest.html");//file:///android_asset/webtest.html
        contentWebView.addJavascriptInterface(this,"android");
        contentWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });


        //Button按钮 无参调用HTML js方法
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 无参数调用 JS的方法
                contentWebView.loadUrl("javascript:javacalljs()");

            }
        });
        //Button按钮 有参调用HTML js方法
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 传递参数调用JS的方法
                contentWebView.loadUrl("javascript:javacalljswith(" + "'http://blog.csdn.net/Leejizhou'" + ")");
            }
        });

    }

    //由于安全原因 targetSdkVersion>=17需要加 @JavascriptInterface
    //JS调用Android JAVA方法名和HTML中的按钮 onclick后的别名后面的名字对应
    @JavascriptInterface
    public void startFunction(){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(WebActivity.this,"show",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String text){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                new AlertDialog.Builder(WebActivity.this).setMessage(text).show();

            }
        });


    }
}
