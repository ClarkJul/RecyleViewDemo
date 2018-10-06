package com.example.clark.recycleviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by Clark, Date on 2018/10/6.
 * PS: Not easy to write code, please indicate.
 */
public class UpdateStaggeredAdapter extends MyRecycleViewAdapter {


    //瀑布流的高度
    List<Integer> mHeight =new ArrayList<>();

    public UpdateStaggeredAdapter(Context mContext, List<String> mDatas) {
        super(mContext, mDatas);

        //初始化瀑布流Item的高度
        for (int i=0;i<mDatas.size();i++){
            mHeight.add((int) (100+Math.random()*300));
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        //设置Item的高度
        ViewGroup.LayoutParams params = myViewHolder.itemView.getLayoutParams();
        params.height=mHeight.get(position);
        myViewHolder.itemView.setLayoutParams(params);

        //设置Item显示的内容
        String str = mDatas.get(position);
        myViewHolder.textView.setText(str);
        setUpdateItemEvent(myViewHolder);
    }

}
