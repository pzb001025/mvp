package com.example.mallshop.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.angle.mvplib.manager.MvpFragmentManager;
import com.example.mallshop.R;
import com.example.mallshop.databinding.ActivityDetailGoodBinding;
import com.example.mallshop.home.fragment.DetailFragment;
import com.example.mallshop.home.fragment.DetailGoodFragment;

public class DetailGoodActivity extends AppCompatActivity {

    private ActivityDetailGoodBinding root;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = ActivityDetailGoodBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        initView();
    }

    private void initView() {
        intent = getIntent();
        int type = intent.getIntExtra("type", 1);
        if (type == 0) {
            MvpFragmentManager.hidOrShowFramgent(getSupportFragmentManager()
                    , DetailFragment.class, null, root.fl.getId());
        } else {
            MvpFragmentManager.hidOrShowFramgent(getSupportFragmentManager()
                    , DetailGoodFragment.class, null, root.fl.getId());
        }
    }
}