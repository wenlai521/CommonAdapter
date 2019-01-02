package com.example.admin.mydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private MaveView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = findViewById(R.id.maveView);
        final View btn = findViewById(R.id.btn1);

        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2,-2);
        lp.gravity = Gravity.CENTER;
        mView.setOnWaveAnimationListener(new MaveView.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                Log.e("y的值","==="+y);
                //在这里动态设置图标的位置
                lp.setMargins(0,0,0,-(int)y+(btn.getHeight()/2));
                btn.setLayoutParams(lp);
            }
        });

    }
}
