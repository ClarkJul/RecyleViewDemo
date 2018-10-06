package com.example.clark.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class StaggeredActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> mDatas;
//    private StaggeredAdapter adapter;
    private UpdateStaggeredAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered);

        initDatas();
        initListeners();
        initViews();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rv_staggered);
        //设置适配器
        adapter = new UpdateStaggeredAdapter(this, mDatas);
        recyclerView.setAdapter(adapter);
        //设置瀑布流的布局管理器
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);//设置竖向排列
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter.setOnItemClidkListener(new MyRecycleViewAdapter.OnItemClidkListener() {
            @Override
            public void onClickListener(View view, int position) {
                adapter.deleteItem(position);
            }

            @Override
            public void onLongClickListener(View view, int position) {

            }
        });
    }

    private void initListeners() {

    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'Z'; i++) {
            mDatas.add((char) i + "");
        }
    }
}
