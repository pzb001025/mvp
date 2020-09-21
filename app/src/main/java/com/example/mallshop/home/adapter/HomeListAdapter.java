package com.example.mallshop.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mallshop.R;
import com.example.mallshop.home.entity.IndexData;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2020/9/15  16:40
 * Author: Lenovo.pzb
 * Project: MallShop
 */
public class HomeListAdapter extends BaseMultiItemQuickAdapter<IndexData.IndexListData, BaseViewHolder> implements LifecycleOwner {

    private Context context;
    private String priceWord;
    private TopicAdapter topicAdapter;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data    A new list is created out of this one to avoid mutable list
     * @param context
     */
    public HomeListAdapter(List<IndexData.IndexListData> data, Context context) {
        super(data);
        this.context = context;
        priceWord = context.getString(R.string.word_price_brand);
        //做UI绑定
        addItemType(IndexData.ITEM_TYPE_BANNER, R.layout.layout_home_banner);
        addItemType(IndexData.ITEM_TYPE_TAB, R.layout.layout_home_tab);
        addItemType(IndexData.ITEM_TYPE_TITLE_TOP, R.layout.layout_title_top);
        addItemType(IndexData.ITEM_TYPE_BRAND, R.layout.layout_home_brand);
        addItemType(IndexData.ITEM_TYPE_TITLE, R.layout.layout_title);
        addItemType(IndexData.ITEM_TYPE_NEW, R.layout.layout_home_newgood);
        addItemType(IndexData.ITEM_TYPE_HOT, R.layout.layout_home_hot);
        addItemType(IndexData.ITEM_TYPE_TOPIC, R.layout.layout_home_topiclist);
        addItemType(IndexData.ITEM_TYPE_CATEGORY, R.layout.layout_home_category);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexData.IndexListData item) {
        switch (item.getItemType()) {
            case IndexData.ITEM_TYPE_TITLE:
                updateTitle(helper, (String) item.data);
                break;
            case IndexData.ITEM_TYPE_TITLE_TOP:
                updateTitle(helper, (String) item.data);
                break;
            case IndexData.ITEM_TYPE_BANNER:
                updateBanner(helper, (List<IndexData.BannerBean>) item.data);
                break;
            case IndexData.ITEM_TYPE_TAB:
                updateTab(helper, (List<IndexData.ChannelBean>) item.data);
                break;
            case IndexData.ITEM_TYPE_BRAND:
                updateBrand(helper, (IndexData.BrandListBean) item.data);
                break;
            case IndexData.ITEM_TYPE_NEW:
                updateNewGood(helper, (IndexData.NewGoodsListBean) item.data);
                break;
            case IndexData.ITEM_TYPE_HOT:
                udpateHot(helper, (IndexData.HotGoodsListBean) item.data);
                break;
            case IndexData.ITEM_TYPE_TOPIC:
                updateTopic(helper, (List<IndexData.TopicListBean>) item.data);
                break;
            case IndexData.ITEM_TYPE_CATEGORY:
                updateCategory(helper, (IndexData.CategoryListBean.GoodsListBean) item.data);
                break;
        }
    }

    /**
     * 更新商品数据
     *
     * @param helper
     * @param data
     */
    private void updateCategory(BaseViewHolder helper, IndexData.CategoryListBean.GoodsListBean data) {
        ImageView img_category = helper.getView(R.id.img_category);
        TextView txt_category_name = helper.getView(R.id.txt_category_name);
        TextView txt_category_price = helper.getView(R.id.txt_category_price);
        Glide.with(context).load(data.getList_pic_url()).placeholder(R.mipmap.ic_launcher).into(img_category);
        txt_category_name.setText(data.getName());
        txt_category_price.setText(data.getRetail_price()+"");
    }

    /**
     * 刷新专题
     *
     * @param helper
     * @param data
     */
    private void updateTopic(BaseViewHolder helper, List<IndexData.TopicListBean> data) {
        RecyclerView recyclerviewTopic = helper.getView(R.id.recyclerviewTopic);
        if (topicAdapter == null) {
            topicAdapter = new TopicAdapter(context, data);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerviewTopic.setLayoutManager(linearLayoutManager);
            recyclerviewTopic.setAdapter(topicAdapter);
        } else if (recyclerviewTopic.getAdapter() == null) {
            recyclerviewTopic.setAdapter(topicAdapter);
        }
    }

    /**
     * 刷新人气数据
     *
     * @param helper
     * @param data
     */
    private void udpateHot(BaseViewHolder helper, IndexData.HotGoodsListBean data) {
        if (!TextUtils.isEmpty(data.getList_pic_url())) {
            Glide.with(context).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.img_hot));
        }
        helper.setText(R.id.txt_hot_name, data.getName());
        helper.setText(R.id.txt_hot_title, data.getGoods_brief());
        helper.setText(R.id.txt_hot_price, String.valueOf(data.getRetail_price()));
    }

    /**
     * 数据新品
     *
     * @param helper
     * @param data
     */
    private void updateNewGood(BaseViewHolder helper, IndexData.NewGoodsListBean data) {
        if (!TextUtils.isEmpty(data.getList_pic_url())) {
            Glide.with(context).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.img_newgood));
        }
        helper.setText(R.id.txt_newgood_name, data.getName());
        String price = priceWord.replace("$", String.valueOf(data.getRetail_price()));
        helper.setText(R.id.txt_newgood_price, price);

    }

    /**
     * 刷新品牌
     *
     * @param helper
     * @param data
     */
    private void updateBrand(BaseViewHolder helper, IndexData.BrandListBean data) {
        if (!TextUtils.isEmpty(data.getNew_pic_url())) {
            Glide.with(context).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.img_brand));
        }
        helper.setText(R.id.txt_brand_name, data.getName());
        String price = priceWord.replace("$", String.valueOf(data.getFloor_price()));
        helper.setText(R.id.txt_brand_price, price);
    }

    /**
     * 刷新channel
     *
     * @param helper
     * @param data
     */
    private void updateTab(BaseViewHolder helper, List<IndexData.ChannelBean> data) {
        LinearLayout layoutChannel = helper.getView(R.id.layout_tab);
        //当前布局内容添加一次 only one
        if (layoutChannel.getChildCount() == 0) {
            for (IndexData.ChannelBean datum : data) {
                TextView tab = new TextView(context);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                tab.setTextSize(14);
                tab.setGravity(Gravity.CENTER);
                tab.setText(datum.getName());
                tab.setLayoutParams(layoutParams);
                layoutChannel.addView(tab);
            }
        }

    }

    /**
     * 刷新title
     *
     * @param viewHolder
     * @param data
     */
    private void updateTitle(BaseViewHolder viewHolder, String data) {
        viewHolder.setText(R.id.txt_title, data);
    }

    /**
     * 刷新banner
     *
     * @param viewHolder
     * @param data
     */
    private void updateBanner(BaseViewHolder viewHolder, List<IndexData.BannerBean> data) {
        Banner banner = viewHolder.getView(R.id.banner);

        List<String> imgs = new ArrayList<>();
        if (imgs.size() > 0) {
            imgs.clear();
        }
        for (int i = 0; i < data.size(); i++) {
            imgs.add(data.get(i).getImage_url());
        }
        banner.setImages(imgs)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                })
                .start();

    }


    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
