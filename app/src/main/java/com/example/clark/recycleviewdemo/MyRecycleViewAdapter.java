package com.example.clark.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Author by clark, Date on 2018/10/6.
 * PS: Not easy to write code, please indicate.
 */
public class MyRecycleViewAdapter extends Adapter<MyRecycleViewAdapter.MyViewHolder> {

    protected Context mContext;
    protected List<String> mDatas;
    protected LayoutInflater mInflater;

    /**
     * item的点击事件可以通过注册View的点击事件实现
     * 也可以自定义item的点击事件
     * <p>
     * 自定义创建item的点击事件接口
     */
    public interface OnItemClidkListener {
        void onClickListener(View view, int position);

        void onLongClickListener(View view, int position);
    }

    private OnItemClidkListener onItemClidkListener;

    //给外界提供点击事件的方法setOnItemClidkListener
    public void setOnItemClidkListener(OnItemClidkListener listener) {
        this.onItemClidkListener = listener;
    }


    public MyRecycleViewAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = mInflater.inflate(R.layout.recycleview_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int position) {
        String str = mDatas.get(position);
        myViewHolder.textView.setText(str);
        setUpdateItemEvent(myViewHolder);

    }

    protected void setUpdateItemEvent(final MyViewHolder myViewHolder) {
        //给item注册点击事件
        if (onItemClidkListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = myViewHolder.getLayoutPosition();
                    onItemClidkListener.onClickListener(myViewHolder.itemView, layoutPosition);

                }
            });
            myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = myViewHolder.getLayoutPosition();
                    onItemClidkListener.onLongClickListener(myViewHolder.itemView, layoutPosition);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //增加item的方法
    public void addItem(int position) {
        mDatas.add(position, "Insert one");
        notifyItemInserted(position);
//        notifyDataSetChanged();//采用notifyDataSetChanged()没有动画效果，能够实现功能
    }

    //删除item的方法
    public void deleteItem(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RelativeLayout recycleItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_text);
            recycleItem = itemView.findViewById(R.id.recycle_item);
        }

    }

}
