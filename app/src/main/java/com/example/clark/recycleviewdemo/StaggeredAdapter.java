package com.example.clark.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StaggeredAdapter extends Adapter<StaggeredHolder> {

    private Context mContext;
    private List<String> mDatas;
    private LayoutInflater mInflater;

    //瀑布流的高度
    List<Integer> mHeight =new ArrayList<>();

    public StaggeredAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(mContext);

        //初始化瀑布流Item的高度
        for (int i=0;i<mDatas.size();i++){
            mHeight.add((int) (100+Math.random()*300));
        }
    }

    @Override
    public StaggeredHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = mInflater.inflate(R.layout.recycleview_item, viewGroup, false);
        StaggeredHolder staggeredHolder = new StaggeredHolder(view);
        return staggeredHolder;
    }

    @Override
    public void onBindViewHolder(StaggeredHolder staggeredHolder, int position) {
        //设置Item的高度
        ViewGroup.LayoutParams params = staggeredHolder.itemView.getLayoutParams();
        params.height=mHeight.get(position);
        staggeredHolder.itemView.setLayoutParams(params);

        //设置Item显示的内容
        String str = mDatas.get(position);
        staggeredHolder.textView.setText(str);
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


}

class StaggeredHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public StaggeredHolder(View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.tv_text);
    }
}
