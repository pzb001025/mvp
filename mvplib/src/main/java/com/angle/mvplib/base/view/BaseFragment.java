package com.angle.mvplib.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.angle.mvplib.R;
import com.trello.rxlifecycle4.components.support.RxFragment;

public abstract class BaseFragment extends RxFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            return null;
        }

        View view = inflater.inflate(getLayoutId(), container, false);


//        获得当前布局(使用布局嵌套)
        String viewName = view.getClass().getName();
        if (viewName == RelativeLayout.class.getName()
                || viewName == ContentFrameLayout.class.getName()
                || viewName == ConstraintLayout.class.getName()
                || viewName == FrameLayout.class.getName()) {
            return view;

        } else {
//            如果不是可以嵌套的布局使用Famelayout作为容器添加
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.addView(view);
            frameLayout.setTag("wrap");
            return frameLayout;
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        如果绑定View有tag说明该布局使用了布局嵌套
        if (view.getTag() != null) {
//            获得子布局使用View绑定
            bindingView(((ViewGroup) view).getChildAt(0));
        } else {
//            如果布局是以上可以嵌套布局直接使用View绑定
            bindingView(view);
        }
//        生成View方法
        initVeiw();
    }

    protected abstract void initVeiw();


    protected void bindingView(View view) {

    }

    protected abstract int getLayoutId();

    //  设置吐司
    public void Toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void Toast(@StringRes int msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //    设置Fragment 进出场动画
    public int AnimationEnter() {
        return R.anim.common_page_left_in;
    }

    public int AnimationExit() {
        return R.anim.common_page_right_out;
    }

    public int AnimationPopEnter() {
        return R.anim.common_page_right_in;
    }

    public int AnimationPopExit() {
        return R.anim.common_page_left_out;
    }

    //    Fragment是否加入回退栈
    public boolean isNeedAddToBackStacl() {
        return true;
    }

//    对上一个Fragment进行处理

    public enum Action {
        NONE, DETACHED, HIDE, REMOVE
    }

    public Action handPerFragment() {
        return Action.HIDE;
    }

}
