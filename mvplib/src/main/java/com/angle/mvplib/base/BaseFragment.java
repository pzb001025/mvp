package com.angle.mvplib.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.angle.mvplib.R;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(getLayoutId(),container,false);

        FrameLayout frameLayout = new FrameLayout(getContext());

        frameLayout.addView(v);

        return frameLayout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();


    public  <T extends View> T findViewById(@IdRes int id){
        return getView().findViewById(id);
    }


    protected void showToast(String content){
        Toast.makeText(getContext(),content,Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int id){
        Toast.makeText(getContext(),id,Toast.LENGTH_SHORT).show();
    }

    public int  getEnterAnimation(){
        return R.anim.common_page_right_in;
    }

    public int  getExitAnimation(){
        return R.anim.common_page_left_out;
    }
    public int  getPopEnterAnimation(){
        return R.anim.common_page_left_in;
    }
    public int  getPopExitAnimation(){
        return R.anim.common_page_right_out;
    }
    public boolean isNeedAddToBackStack(){
        return true;
    }

    public Action getActionFroPreFragment(){
        return Action.HIDE;
    }

    /**
     * 对上一个fragment 如何进行处理
     */
    public enum Action{
        NONE,HIDE,DETACH,REMOVE
    }





}
