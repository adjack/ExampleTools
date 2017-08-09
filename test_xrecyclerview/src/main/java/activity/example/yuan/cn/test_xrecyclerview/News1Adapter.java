package activity.example.yuan.cn.test_xrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jianghejie on 15/11/26.
 */
public class News1Adapter extends RecyclerView.Adapter<News1Adapter.ViewHolder> {
    public ArrayList<String> datas = null;
    public News1Adapter(ArrayList<String> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_new1,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.tv_title.setText(datas.get(position));
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_logo;
        public TextView tv_title;
        public TextView tv_content;
        public TextView tv_date;
        public ViewHolder(View view){
            super(view);
            iv_logo = (ImageView) view.findViewById(R.id.iv_logo);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_content = (TextView) view.findViewById(R.id.tv_content);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
        }
    }
}
