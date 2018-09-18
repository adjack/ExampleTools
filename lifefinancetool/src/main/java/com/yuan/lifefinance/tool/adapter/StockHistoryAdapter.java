package com.yuan.lifefinance.tool.adapter;

import android.content.Context;
import android.widget.TextView;

import com.yuan.lifefinance.tool.R;
import com.yuan.lifefinance.tool.adapter.recyclebase.ListBaseAdapter;
import com.yuan.lifefinance.tool.adapter.recyclebase.SuperViewHolder;
import com.yuan.lifefinance.tool.greendao.StockInfo;

/**
 * Created by 123 on 2018/9/18.
 */

public class StockHistoryAdapter extends ListBaseAdapter<StockInfo> {
    private Context context;
    public StockHistoryAdapter(Context context) {
        super(context);
        this.context = context;
    }
    @Override
    public int getLayoutId() {
        return R.layout.item_history;
    }

    @Override
    public void onViewRecycled(SuperViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int pos) {
        TextView tv_name =  holder.getView(R.id.tv_name);
        tv_name.setText(mDataList.get(pos).getStokeName());
    }

}
