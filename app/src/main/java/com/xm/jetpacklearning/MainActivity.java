package com.xm.jetpacklearning;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.xm.jetpacklearning.databinding.ActivityMainBinding;

/**
 * 视图绑定有啥作用：
 *      不需要findViewById
 *          null安全。不会有id无效所引发的Null指针风险（id存在但不能在当前view中找到）
 *          类型安全。避免类型转换带来的安全问题（ClassCastException）
 *      和数据绑定的关系：
 *          快，易用
 *          但不支持布局变量表达式（@{}）以及不支持双向数据绑定
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        View root  = activityMainBinding.getRoot();
        setContentView(root);

        // 获取布局中有id的某个元素
        activityMainBinding.btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityMainBinding.btnButton.setText("after click");
            }
        });
    }
}