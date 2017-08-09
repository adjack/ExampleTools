package activity.example.yuan.cn.test_xrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jianghejie on 15/11/26.
 */
public class News2Adapter extends RecyclerView.Adapter<News2Adapter.ViewHolder> {
    public ArrayList<String> datas = null;
    public News2Adapter(ArrayList<String> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_new2,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
//        viewHolder.tv_title.setText(datas.get(position));
        viewHolder.tv_Reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.rl_comment.setVisibility(viewHolder.rl_comment.getVisibility() == View.GONE?View.VISIBLE:View.GONE);
            }
        });
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_Reply;
        public RelativeLayout rl_comment;
        public ViewHolder(View view){
            super(view);
            tv_Reply =  view.findViewById(R.id.tv_Reply);
            rl_comment = view.findViewById(R.id.rl_comment);
        }
    }
}
