package com.example.mallshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toolbar;

import com.example.mallshop.classify.fragment.ClassifyFragment;
import com.example.mallshop.databinding.ActivityMainBinding;
import com.example.mallshop.home.fragment.HomeFragment;
import com.example.mallshop.my.fragment.MyFragment;
import com.example.mallshop.shop.fragment.ShopFragment;
import com.example.mallshop.special.fragment.SpecialFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding root;
    private HomeFragment homeFragment;
    private MyFragment myFragment;
    private ClassifyFragment classifyFragment;
    private ShopFragment shopFragment;
    private SpecialFragment specialFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        initView();
    }

    private void initView() {
        getSupportActionBar();
        homeFragment = new HomeFragment();
        specialFragment = new SpecialFragment();
        classifyFragment = new ClassifyFragment();
        shopFragment = new ShopFragment();
        myFragment = new MyFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fl, homeFragment)
                .add(R.id.fl, specialFragment)
                .add(R.id.fl, classifyFragment)
                .add(R.id.fl, shopFragment)
                .add(R.id.fl, myFragment)
                .show(homeFragment)
                .hide(specialFragment).hide(classifyFragment).hide(shopFragment).hide(myFragment)
                .commit();

        root.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                hideFragmentAll();
                FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rbHome:
                        fm.show(homeFragment).commit();
                        root.toolbar.setTitle("首页");
                        break;
                    case R.id.rbSpecial:
                        fm.show(specialFragment).commit();
                        root.toolbar.setTitle("专题");
                        break;
                    case R.id.rbClassify:
                        fm.show(classifyFragment).commit();
                        root.toolbar.setTitle("分类");
                        break;
                    case R.id.rbShop:
                        fm.show(shopFragment).commit();
                        root.toolbar.setTitle("购物车");
                        break;
                    case R.id.rbMe:
                        fm.show(myFragment).commit();
                        root.toolbar.setTitle("我的");
                        break;
                }
            }
        });
        root.rbHome.setChecked(true);
    }

    public void hideFragmentAll() {
        getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(specialFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(classifyFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(shopFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(myFragment).commit();
    }
}