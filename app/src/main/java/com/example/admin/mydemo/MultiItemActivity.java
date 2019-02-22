package com.example.admin.mydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MultiItemActivity extends AppCompatActivity {

    private RecyclerView list;
    private List<UserEntity> mData = new ArrayList<UserEntity>();
    private static final int TYPE_HEAD = 1;
    private static final int TYPE_COMMON = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item);


        for (int i = 0; i < 20; i++) {
            mData.add(new UserEntity("secondadapter" + i, "通用adapter" + i, i));
        }


        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        MultiItemTypeSupport<UserEntity> support = new MultiItemTypeSupport<UserEntity>() {

            @Override
            public int getLayoutId(int itemType) {

                if (itemType == TYPE_HEAD) {

                    return R.layout.item_special;
                } else {

                    return R.layout.item_common;
                }
            }

            @Override
            public int getItemViewType(int position, UserEntity s) {

                if (position % 2 == 0) {

                    return TYPE_HEAD;
                } else {

                    return TYPE_COMMON;
                }
            }
        };
//        -----------------------------------------------------------------------------------------------------------------------------
        MultiItemAdapter<UserEntity> mAdapter = new MultiItemAdapter<UserEntity>(this, mData, support) {

            @Override
            public void bindHolder(CommonHolder holder, UserEntity userEntity, int type) {
                switch (type) {
                    case TYPE_HEAD:
                        holder.setText(R.id.text, userEntity.getUserHome());
                        break;
                    case TYPE_COMMON:
                        holder.setText(R.id.text2, userEntity.getUserName());
                        break;
                }
            }

        };
        list.setAdapter(mAdapter);

    }
}
