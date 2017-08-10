package activity.example.yuan.cn.test_citylist.city;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Android防止ListView中Item被重复点�?
 */
public abstract class OnItemClickAvoidForceListener implements
        OnItemClickListener {

	private long lastOnClickTime = 0;

	private long lockTime = 750;

	public boolean isSmoothClick() {
		boolean isSmooth = true;
		long current = System.currentTimeMillis();

		if (0 == lastOnClickTime
				|| Math.abs(current - lastOnClickTime) > lockTime) {
			lastOnClickTime = current;
			isSmooth = true;
		} else {
			isSmooth = false;
		}
		return isSmooth;
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

		if (isSmoothClick()) {

			onItemClickAvoidForce(parent, view, position, id);
		}
	}

	public abstract void onItemClickAvoidForce(AdapterView<?> parent,
                                               View view, int position, long id);

}
