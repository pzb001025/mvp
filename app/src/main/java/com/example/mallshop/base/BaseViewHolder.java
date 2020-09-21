package com.example.mallshop.base;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        views = new SparseArray<>();
    }

    private void setTextView(@IdRes int id,int content){
        View view = views.get(id);
        if(view == null){
            try {
                throw new Exception("请先调用getView保存View先");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(view instanceof TextView){
            ((TextView) view).setText(content);
        }else{
            try {
                throw new Exception("这个id不是一个TextView视图");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private <T extends View> T getView(@IdRes int viewId){
        View view = views.get(viewId);
        if(view == null){
            view = itemView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T) view;
    }
}

