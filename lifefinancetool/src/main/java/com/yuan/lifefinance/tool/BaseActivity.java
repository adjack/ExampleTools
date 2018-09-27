package com.yuan.lifefinance.tool;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 123 on 2018/9/27.
 */

public abstract class BaseActivity extends Activity{

    private Unbinder mUnbinder;
    private View mContextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);
        setContentView(mContextView);
        mUnbinder = ButterKnife.bind(this);
        initData();
    }

    abstract int bindLayout();
    abstract void initData();

    //初始化通用recyclerView
    protected void initRecyclerView(LRecyclerView lRecyclerView, LRecyclerViewAdapter lRecyclerViewAdapter){
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        lRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
    }

    //计算列表数据页码
    protected int getMaxPage(int maxNum,int pageSize){
        int value1 = maxNum / pageSize;
        int value2 = maxNum % pageSize;
        if(value2 == 0){
            return value1 + 1;
        }
        else{
            return value1+2;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) mUnbinder.unbind();
    }


}
