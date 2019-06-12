package com.image.download;

import java.util.ArrayList;
import java.util.List;

import com.example.imagedownload.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class PhotoActivity extends Activity {

	/** GridView实例 */
	private GridView gridView;

	/** GridView适配器 */
	private ImageGridViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_activity);

		List<String> images = new ArrayList<String>();
		// 服务器图片地址
		String ip = "http://192.168.83.213/upload/";
		for (int i = 0; i < 52; i++) {
			int name = i + 1;
			String url = ip + name + ".jpg";
			images.add(url);
		}
		gridView = (GridView) findViewById(R.id.photo_wall);
		adapter = new ImageGridViewAdapter(this, gridView, images);
		gridView.setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		adapter.cancelTasks();
	}
}
