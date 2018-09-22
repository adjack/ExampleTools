package com.yuan.lifefinance.tool.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuan.lifefinance.tool.R;
import com.yuan.lifefinance.tool.adapter.recyclebase.ListBaseAdapter;
import com.yuan.lifefinance.tool.adapter.recyclebase.SuperViewHolder;
import com.yuan.lifefinance.tool.greendao.StockInfo;
import com.yuan.lifefinance.tool.tools.DoubleTools;
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
        RelativeLayout root_view = holder.getView(R.id.root_view);
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
        LinearLayout linear_DayNum = holder.getView(R.id.linear_DayNum);
        TextView tv_DayNum =  holder.getView(R.id.tv_DayNum);
        TextView tv_bili = holder.getView(R.id.tv_bili);

        tv_name.setText(mDataList.get(pos).getStokeName());
        tv_cost.setText("成本："+mDataList.get(pos).getCost());
        tv_stopLoss.setText("止损："+mDataList.get(pos).getStopLoss());
        try {
            double value2 = mDataList.get(pos).getMostPrice()- Double.valueOf(mDataList.get(pos).getCost());
            double value3 = Double.valueOf(DoubleTools.dealMaximumFractionDigits(value2/Double.valueOf(mDataList.get(pos).getCost())*100,2));
            tv_bili.setText(DoubleTools.dealMaximumFractionDigits(value3,2)+"%");
        }
        catch (Exception ex){
        }
        tv_mostPrice.setText("目标："+mDataList.get(pos).getMostPrice()+" ¥");
        initRValueColor(tv_rValueFlag,tv_rValue,mDataList.get(pos).getRValue());
        tv_timeInfo.setText(dealTime(mDataList.get(pos).getTimeInfoBuy()));

        tv_salePrice.setText(dealSalePrice(mDataList.get(pos).getSalePrice()));
        Log.d("getIncome","getIncome:"+mDataList.get(pos).getIncome()+"/"+mDataList.get(pos).getIncome());
        initIncome(tv_incomeFlag,tv_income,mDataList.get(pos).getIncome());
        String timeInfoSale = StringInputUtils.value(mDataList.get(pos).getTimeInfoSale());


        if(TextUtils.isEmpty(timeInfoSale)){//2018092115:27:53
//            long value = new Date().getTime() - dealBugTime("2018091815:27:53");
            long value = new Date().getTime() - dealBugTime(mDataList.get(pos).getTimeInfoBuy());
            linear_DayNum.setVisibility(value > 0?View.VISIBLE:View.GONE);
            if(value > 0){
                tv_DayNum.setText(value/1000/60/60/24+"");
            }
        }
        else{
            long value = dealBugTime(mDataList.get(pos).getTimeInfoSale()) - dealBugTime(mDataList.get(pos).getTimeInfoBuy());
            linear_DayNum.setVisibility(value > 0?View.VISIBLE:View.GONE);
            if(value > 0){
                tv_DayNum.setText(value/1000/60/60/24+"");
            }
        }
        Log.d("linear_DayNum","timeInfoSale:"+timeInfoSale);

        root_view.setBackgroundResource(TextUtils.isEmpty(timeInfoSale)?R.drawable.item_shape_01:R.drawable.item_shape_02);
//        root_view.setBackgroundColor(Color.parseColor(TextUtils.isEmpty(timeInfoSale)?"#fffff0":"#F2F2F2"));

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

    private long dealBugTime(String timeV){
        long value = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH:mm:ss");
        Date date = null;
        try {
            date = format.parse(timeV);
            value = date.getTime();
        } catch (ParseException e) {
        }
        return value;
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
