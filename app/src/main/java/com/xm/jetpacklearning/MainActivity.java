package com.xm.jetpacklearning;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.xm.jetpacklearning.databinding.ActivityMainBinding;

/**
 * 视图绑定有啥作用：
 * 不需要findViewById
 * null安全。不会有id无效所引发的Null指针风险（id存在但不能在当前view中找到）
 * 类型安全。避免类型转换带来的安全问题（ClassCastException）
 * 和数据绑定的关系：
 * 快，易用
 * 但不支持布局变量表达式（@{}）以及不支持双向数据绑定
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private UserViewModel userViewModel;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(activityMainBinding.getRoot());

        final User user = new User();
        activityMainBinding.setUser(user);

        // 布局绑定：获取布局中有id的某个元素
        activityMainBinding.btnChangeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 数据绑定（动态变化）
                user.firstName.set("xiaomei");
                user.lastName.set("zhu");

                count++;
                userViewModel.getName().setValue("name" + count);
            }
        });

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String name) {
                activityMainBinding.tvName.setText(name);
            }
        };

        userViewModel.getName().observe(this, nameObserver);
    }
}