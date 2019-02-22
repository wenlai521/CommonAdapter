package com.example.admin.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<UserEntity> mData = new ArrayList<UserEntity>() {
        {
            for (int i = 0; i < 10; i++) {
                UserEntity userEntity1 = new UserEntity("secondadapter" + i, "通用adapter" + i, 0 + i);
                add(userEntity1);
            }
        }
    };
    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(new CommonAdapter<UserEntity>(mData, this, R.layout.second_adapter) {
            @Override
            public void bindHolder(CommonHolder holder, UserEntity userEntity) {
                holder.setText(R.id.text1, userEntity.getUserName());
                holder.setText(R.id.text2, userEntity.getUserName());
                holder.setText(R.id.text3, userEntity.getUserName());
                holder.setImg(R.id.img, R.mipmap.ic_launcher);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                        MainActivity.this.startActivity(new Intent(MainActivity.this, MultiItemActivity.class));
                    }
                });
                holder.setClickListener(R.id.layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                        MainActivity.this.startActivity(new Intent(MainActivity.this, MultiItemActivity.class));
                    }
                });
            }
        });

    }
}
