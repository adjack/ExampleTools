package com.yuan.lifefinance.tool.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import com.yuan.lifefinance.tool.R;
import com.yuan.lifefinance.tool.adapter.recyclebase.ListBaseAdapter;
import com.yuan.lifefinance.tool.adapter.recyclebase.SuperViewHolder;
import com.yuan.lifefinance.tool.greendao.StockInfo;
import com.yuan.lifefinance.tool.tools.StringInputUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        TextView tv_cost =  holder.getView(R.id.tv_cost);
        TextView tv_stopLoss =  holder.getView(R.id.tv_stopLoss);
        TextView tv_mostPrice =  holder.getView(R.id.tv_mostPrice);
        TextView tv_income =  holder.getView(R.id.tv_income);
        TextView tv_incomeFlag =  holder.getView(R.id.tv_incomeFlag);
        TextView tv_rValue =  holder.getView(R.id.tv_rValue);
        TextView tv_rValueFlag =  holder.getView(R.id.tv_rValueFlag);
        TextView tv_timeInfo =  holder.getView(R.id.tv_timeInfo);
        TextView tv_salePrice =  holder.getView(R.id.tv_salePrice);
        tv_name.setText(mDataList.get(pos).getStokeName());
        tv_cost.setText("成本："+mDataList.get(pos).getCost());
        tv_stopLoss.setText("止损："+mDataList.get(pos).getStopLoss());
        tv_mostPrice.setText("最高："+mDataList.get(pos).getMostPrice()+" ¥");
        initRValueColor(tv_rValueFlag,tv_rValue,mDataList.get(pos).getRValue());
        tv_timeInfo.setText(dealTime(mDataList.get(pos).getTimeInfoBuy()));

        tv_salePrice.setText(dealSalePrice(mDataList.get(pos).getSalePrice()));
        Log.d("getIncome","getIncome:"+mDataList.get(pos).getIncome()+"/"+mDataList.get(pos).getIncome());
        initIncome(tv_incomeFlag,tv_income,mDataList.get(pos).getIncome());
    }

    private String dealSalePrice(String salePrice){
        if(StringInputUtils.value(salePrice).isEmpty()){
            return "";
        }
        return "卖出价格："+salePrice+" ¥";

    }

    private String dealTime(String timeV){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH:mm:ss");
        SimpleDateFormat formatNew = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = format.parse(timeV);
        } catch (ParseException e) {
        }
        return formatNew.format(date);
    }

    private void initRValueColor(TextView textViewFlag,TextView tv_rValue,double rValue){
        try {
            tv_rValue.setText(rValue+"");
            int colorValue = Color.parseColor(rValue < 3?"#006400":"#d7000f");
            textViewFlag.setTextColor(colorValue);
            tv_rValue.setTextColor(colorValue);
        }catch (Exception ex){}
    }
    private String initIncome(TextView tv_incomeFlag,TextView tv_income,String value){
        if(value == null || value.isEmpty() || value.equals("null")) value = "--";
        if(!value.equals("--")){
            int colorValue = Color.parseColor(value.contains("-")?"#006400":"#d7000f");
            tv_incomeFlag.setTextColor(colorValue);
            tv_income.setTextColor(colorValue);
            tv_income.setText(value);
        }
        return value;
    }

}
