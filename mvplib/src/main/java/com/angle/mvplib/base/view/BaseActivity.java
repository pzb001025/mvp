package com.angle.mvplib.base.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.trello.rxlifecycle4.components.RxActivity;

/**
 * getLayoutId（） 绑定xml ID
 * 在 判断 xml ID 绑定使用ViewBinding
 * 这样就可以同时使用getLayoutId（），ViewBinding
 */
public abstract class BaseActivity extends RxActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        如果绑定xml ID
        if (getLayoutId() > 0) {
//            加载布局
            View view = getLayoutInflater().inflate(getLayoutId(), findViewById(android.R.id.content), false);
            setContentView(view);
//            viewBinding
            bindingView(view);
        }
    }

    protected void showToast(@StringRes int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void bindingView(View view) {
    }


    protected abstract int getLayoutId();
}
