package com.yuan.lifefinance.tool;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yuan.lifefinance.tool.greendao.DBManager;
import com.yuan.lifefinance.tool.tools.DoubleTools;
import com.yuan.lifefinance.tool.tools.LogUtil;
import com.yuan.lifefinance.tool.tools.StringInputUtils;
import com.yuan.lifefinance.tool.tools.TimeTools;
import com.yuan.lifefinance.tool.view.CustomHintDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CompareStockActivity extends BaseActivity{

    private boolean permissionIsOk;
    private TextView tv_time;

    private EditText et_name,et_cost,et_stopLoss,et_mostPrice,et_Code;
    private TextView tv_rValue,tv_refreshRValue;//

    @Override
    int bindLayout() {
        return R.layout.activity_comparestock;
    }

    @Override
    void initData() {
        findViewById(R.id.iv_return).setOnClickListener(v->finish());
        initView();
        setDate();
    }

    private void initView(){
        tv_time = findViewById(R.id.tv_time);
        et_name = findViewById(R.id.et_name);
        et_cost = findViewById(R.id.et_cost);
        et_stopLoss = findViewById(R.id.et_stopLoss);
        et_mostPrice = findViewById(R.id.et_mostPrice);
        et_Code = findViewById(R.id.et_Code);

        tv_rValue = findViewById(R.id.tv_rValue);
        tv_refreshRValue = findViewById(R.id.tv_refreshRValue);
    }

    public void onclickSell(View view){
        startActivity(new Intent(CompareStockActivity.this,HistoryInfoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(tv_time != null){
            nowDate = "";
            tv_time.setText("日期："+getNowDate());
        }
    }

    private void setDate(){
        tv_time.setText("日期："+ TimeTools.dealTime(getNowDate()));
    }

    String nowDate = "";
    private String getNowDate(){
        if(TextUtils.isEmpty(nowDate)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH:mm:ss");// HH:mm:ss
            //获取当前时间
            Date date = new Date(System.currentTimeMillis());
            nowDate = simpleDateFormat.format(date);
        }
        return nowDate;
    }

    private boolean isDataOk(){
        if(StringInputUtils.valueIsEmpty(et_name)){
            Toast.makeText(CompareStockActivity.this,"请输入名称！",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StringInputUtils.valueIsEmpty(et_Code) && StringInputUtils.value(et_Code).length() == 6){
            Toast.makeText(CompareStockActivity.this,"股票代码有误！",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StringInputUtils.valueIsEmpty(et_stopLoss)){
            Toast.makeText(CompareStockActivity.this,"止损价格为空！",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StringInputUtils.valueIsEmpty(et_mostPrice)){
            Toast.makeText(CompareStockActivity.this,"目标价格为空！",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StringInputUtils.valueIsEmpty(tv_rValue)){
            Toast.makeText(CompareStockActivity.this,"R比率为空！",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void onclick(View view){
        if(isDataOk()){
            String value = "添加到备选池！";
            new CustomHintDialog(CompareStockActivity.this, ()->savaData(),value,"取消", "添加",CustomHintDialog.Dialog_TYPE_1);
        }

    }

    public void onclickCompare(View view){
//        if(isDataOk()){
//            new CustomHintDialog(MainActivity.this, ()->savaDataToTempStockInfo(),"添加到比对池","取消", "添加",CustomHintDialog.Dialog_TYPE_1);
//        }
    }

    //下单保存
    public void savaData(){
        try {
            //保存数据库信息
            String stokeName = StringInputUtils.value(et_name);
            double stopLoss = Double.valueOf(StringInputUtils.value(et_stopLoss));
            double mostPrice = Double.valueOf(StringInputUtils.value(et_mostPrice));
            double rValue = Double.valueOf(StringInputUtils.value(tv_rValue));//默认拿最低R比率为3条件

            double cost = (mostPrice+rValue*stopLoss)/(1+rValue);//根据最低R比率算出的最低买入价格
            double cost_other = stopLoss/(1-0.03);//根据最大止损3%计算最高买入价格
            double most_cost = cost <= cost_other?cost:cost_other;//最优的买入价格

            //重新计算下R比率用于显示在列表
            rValue = Double.valueOf(DoubleTools.dealMaximumFractionDigits((mostPrice - most_cost)/(most_cost - stopLoss),2));

            int result = DBManager.getInstance().savaTempStockInfo(stokeName, StringInputUtils.value(et_Code),DoubleTools.dealMaximumFractionDigits(most_cost,2),stopLoss,mostPrice,rValue);
            LogUtil.d("savaStockInfo","result:"+result);
            //截图保存
//            String nowdate = getNowDate().replace(" ","");
//            saveToSD(myShot(CompareStockActivity.this),et_name.getText().toString()+"_"+nowdate);
            Toast.makeText(CompareStockActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
            clearData();
            startActivity(new Intent(CompareStockActivity.this,TempStockInfoActivity.class));
            finish();
        }
        catch (Exception ex){
            Toast.makeText(CompareStockActivity.this,ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    private void clearData(){
        et_name.setText("");
        et_cost.setText("");
        et_stopLoss.setText("");
        et_mostPrice.setText("");
        tv_rValue.setText("");
        nowDate = "";
        nowDate = getNowDate();
        tv_time.setText("日期："+TimeTools.dealTime(getNowDate()));
    }
}
