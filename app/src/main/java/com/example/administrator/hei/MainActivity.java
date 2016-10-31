package com.example.administrator.hei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private MyAdapter myAdapter;
    private List<Bean> mData=new ArrayList<Bean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        prepareData();
        mLinearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
         recyclerView.addItemDecoration(new TitleItemDecoration(this,mData));
        myAdapter=new MyAdapter();
        recyclerView.setAdapter(myAdapter);


    }

    private void prepareData() {
       // mData.add(new Bean("A",null));
        for (int i = 0; i <5 ; i++) {
            mData.add(new Bean("A","item"+i));
        }
      //  mData.add(new Bean("B",null));
        for (int i = 5; i <10 ; i++) {
            mData.add(new Bean("B","item"+i));
        }
       // mData.add(new Bean("C",null));
        for (int i = 10; i <15 ; i++) {
            mData.add(new Bean("C","item"+i));
        }
      //  mData.add(new Bean("D",null));
        for (int i = 15; i <20 ; i++) {
            mData.add(new Bean("D","item"+i));
        }
       // mData.add(new Bean("E",null));
        for (int i = 25; i <30 ; i++) {
            mData.add(new Bean("E","item"+i));
        }
        for (int i = 30; i <35 ; i++) {
            mData.add(new Bean("F","item"+i));
        }
        for (int i = 35; i <40 ; i++) {
            mData.add(new Bean("G","item"+i));
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_item
                    ,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Bean item=mData.get(position);
            holder.text.setText(item.getValue());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
    private  static  class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text;

        public MyViewHolder(View itemView) {

            super(itemView);
            text= (TextView) itemView.findViewById(R.id.text);
        }
    }
}
