package activity.example.yuan.cn.exampletools.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * 带进度条的WebView
 * 
 */
@SuppressWarnings("deprecation")
public class ProgressWebView extends WebView implements Pullable{

	private ProgressBar progressbar;

	public ProgressWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		progressbar = new ProgressBar(context, null,android.R.attr.progressBarStyleHorizontal);
		progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				4, 0, 0));
		addView(progressbar);
//		setWebChromeClient(new WebChromeClient());
	}
	
	public ProgressBar getProgressBar(){
		return progressbar;
	}

//	public class WebChromeClient extends android.webkit.WebChromeClient {
//		@Override
//		public void onProgressChanged(WebView view, int newProgress) {
//			if (newProgress == 100) {
//				progressbar.setVisibility(GONE);
//			} else {
//				if (progressbar.getVisibility() == GONE)
//					progressbar.setVisibility(VISIBLE);
//				progressbar.setProgress(newProgress);
//			}
//			Log.d("onProgressChanged",newProgress+"-"); 
//			super.onProgressChanged(view, newProgress);
//		}
//
//	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//		LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
//		lp.x = l;
//		lp.y = t;
//		progressbar.setLayoutParams(lp);
		super.onScrollChanged(l, t, oldl, oldt);
	}

	@Override
	public boolean canPullDown() {
		if (getScrollY() == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean canPullUp() {
//		if (getScrollY() >= getContentHeight() * getScale()
//				- getMeasuredHeight())
//			return true;
//		else
//			return false;
		return false;//限制不能上拉
	}
}
