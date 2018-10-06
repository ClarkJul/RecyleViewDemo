package com.example.clark.recycleviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> mDatas;
    private MyRecycleViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initListeners();
        initViews();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rv_main);
        //设置适配器
        adapter = new MyRecycleViewAdapter(this, mDatas);
        recyclerView.setAdapter(adapter);
        //设置布局管理器
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
        //设置item的分隔
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        //设置动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());//默认的动画效果，通过增加和删除item可以表现出来

        //采用自定义的方式设置item的点击事件
        adapter.setOnItemClidkListener(new MyRecycleViewAdapter.OnItemClidkListener() {
            @Override
            public void onClickListener(View view, int position) {
                Toast.makeText(MainActivity.this,"click"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickListener(View view, int position) {
                Toast.makeText(MainActivity.this,"longclick:"+position,Toast.LENGTH_SHORT).show();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //菜单项的回调方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                adapter.addItem(0);
                break;
            case R.id.action_delete:
                adapter.deleteItem(0);
                break;
            case R.id.listview:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(linearLayoutManager);
                break;
            case R.id.gridview:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);//第二个参数表示列数
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            case R.id.hor_gridview:
                StaggeredGridLayoutManager horLayoutManager=new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);//spanCount设置成1变成listview的样式
                recyclerView.setLayoutManager(horLayoutManager);
                break;
            case R.id.staggered:
                Intent intent = new Intent(MainActivity.this, StaggeredActivity.class);
                startActivity(intent);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
