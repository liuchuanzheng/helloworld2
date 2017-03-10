package com.liuchuanzheng.helloworld2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
    private List<Integer> mHeights;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.RV_1);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //这是自定义的点击监听。以为recyclerView没有提供现成的点击监听，为了让用户完全自定义，防止事件冲突
        mAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(Main5Activity.this,"position:" + position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                removeData(position);
            }
        });
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        mHeights = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() *300));//设置随机的高度
        }
    }

    public static void actionStart(Context context,String data1,String data2){
        Intent intent = new Intent(context,Main5Activity.class);
        intent.putExtra("data1",data1);
        intent.putExtra("data2",data2);
        context.startActivity(intent);
    }
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        private OnClickListener onClickListener = null;
        public void setOnClickListener(OnClickListener onClickListener){
            this.onClickListener = onClickListener;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    Main5Activity.this).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            ViewGroup.LayoutParams LayoutParams  = holder.tv.getLayoutParams();
            LayoutParams.height = mHeights.get(position);
            holder.tv.setLayoutParams(LayoutParams);
            holder.tv.setText(mDatas.get(position));
            if (onClickListener != null){
                holder.tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickListener.onClick(holder.getAdapterPosition());
                    }
                });
                holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onClickListener.onLongClick(holder.getAdapterPosition());
                        return false;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
    public interface OnClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.five,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                addData(6);
                break;
            case R.id.remove:
                removeData(6);
                break;


        }
        return true;
    }
    public void addData(int position) {
        mDatas.add(position, "Insert One");
        mAdapter.notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
