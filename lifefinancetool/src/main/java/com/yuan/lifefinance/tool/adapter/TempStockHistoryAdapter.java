package com.yuan.lifefinance.tool.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.yuan.lifefinance.tool.R;
import com.yuan.lifefinance.tool.adapter.recyclebase.ListBaseAdapter;
import com.yuan.lifefinance.tool.adapter.recyclebase.SuperViewHolder;
import com.yuan.lifefinance.tool.greendao.TempStockInfo;
import com.yuan.lifefinance.tool.tools.DoubleTools;
import com.yuan.lifefinance.tool.tools.StringInputUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 123 on 2018/9/18.
 */

public class TempStockHistoryAdapter extends ListBaseAdapter<TempStockInfo> {
    private Context context;
    public TempStockHistoryAdapter(Context context) {
        super(context);
        this.context = context;
    }
    @Override
    public int getLayoutId() {
        return R.layout.item_temphistory;
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
        TextView tv_stopLossRatio =  holder.getView(R.id.tv_stopLossRatio);
        TextView tv_timeInfo =  holder.getView(R.id.tv_timeInfo);
        TextView tv_salePrice =  holder.getView(R.id.tv_salePrice);
        TextView tv_nowPrice = holder.getView(R.id.tv_nowPrice);
        tv_name.setText(mDataList.get(pos).getStokeName());
        tv_cost.setText("买入成本应少于："+mDataList.get(pos).getCostValue());
        tv_stopLoss.setText("止损："+mDataList.get(pos).getStopLoss());
        tv_mostPrice.setText("目标："+mDataList.get(pos).getMostPrice()+" ¥");
        initRValueColor(tv_rValueFlag,tv_rValue,mDataList.get(pos).getRValue());
        String openPrice = StringInputUtils.value(mDataList.get(pos).getDiscrib3());
        String price = StringInputUtils.value(mDataList.get(pos).getDiscrib2());
        dealNowPrice(tv_nowPrice,openPrice,price);
        //当前成本最高价格买入的最高收益率
        double value2 = mDataList.get(pos).getMostPrice()- Double.valueOf(mDataList.get(pos).getCostValue());
        double value3 = Double.valueOf(DoubleTools.dealMaximumFractionDigits(value2/Double.valueOf(mDataList.get(pos).getCostValue())*100,2));
        tv_income.setText(DoubleTools.dealMaximumFractionDigits(value3,2)+"%");

        double value4 = Double.valueOf(mDataList.get(pos).getCostValue()) - mDataList.get(pos).getStopLoss();
        double value5 = Double.valueOf(DoubleTools.dealMaximumFractionDigits(value4/Double.valueOf(mDataList.get(pos).getCostValue())*100,2));
        tv_stopLossRatio.setText("-"+DoubleTools.dealMaximumFractionDigits(value5,2)+"%");
    }

    private void dealNowPrice(TextView tv_nowPrice,String openPrice,String price){
        tv_nowPrice.setText(price.isEmpty()?"":"最新："+price);
        if(!price.isEmpty() ){
            if(!openPrice.isEmpty()){
                String colorStr = Double.valueOf(price)>Double.valueOf(openPrice)?"#d7000f":"#32CD32";
                tv_nowPrice.setTextColor(Color.parseColor(colorStr));
            }
        }
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
