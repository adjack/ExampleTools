package activity.example.yuan.cn.test_citylist.city;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import activity.example.yuan.cn.test_citylist.R;

public class ListViewAdapter extends BaseAdapter {

	private Context context;
	private List<City> list;
	private ViewHolder viewHolder;

	public ListViewAdapter(Context context, List<City> list2) {

		if (list != null) {
			list.clear();
		}
		this.context = context;
		this.list = list2;
		list.add(1, null);// 添加热门城市的标签
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean isEnabled(int position) {
		return super.isEnabled(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewHolder = new ViewHolder();
		if (position == 0) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.citychoose_locationitem, null);
			viewHolder.itemTv = (TextView) convertView
					.findViewById(R.id.itemTv);
			viewHolder.itemlocation = (TextView) convertView
					.findViewById(R.id.city);
			viewHolder.itemTv.setText("当前城市:");
			viewHolder.itemlocation.setText(list.get(0).getName());
			viewHolder.itemTv.setVisibility(View.GONE);
			viewHolder.itemlocation.setVisibility(View.GONE);

			return convertView;
		}
		if (position == 1) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.citychoose_index, null);
			viewHolder.indexTv = (TextView) convertView
					.findViewById(R.id.indexTv);
			viewHolder.indexTv.setText("热门城市");
			viewHolder.indexTv.setVisibility(View.GONE);
			return convertView;
		}

		String item = list.get(position).getName();
		if (item.length() == 1) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.citychoose_index, null);
			viewHolder.indexTv = (TextView) convertView
					.findViewById(R.id.indexTv);
		} else {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.citychoose_item, null);
			viewHolder.itemTv = (TextView) convertView
					.findViewById(R.id.itemTv);
		}

		if (item.length() == 1) {
			viewHolder.indexTv.setText(list.get(position).getName());
			viewHolder.indexTv.setTextColor(Color.parseColor("#9c9c9c"));
			viewHolder.indexTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,13.0f);
		} else {
			viewHolder.itemTv.setText(list.get(position).getName());
			viewHolder.itemTv.setTextColor(Color.parseColor("#202532"));
			viewHolder.itemTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16.0f);
		}

		return convertView;
	}

	private class ViewHolder {
		private TextView indexTv;
		private TextView itemTv;
		private TextView itemlocation;
	}

}
