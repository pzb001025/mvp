package com.example.mallshop.home.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.angle.mvplib.base.view.BaseSmartFragment1;
import com.angle.mvplib.common.Constarct;
import com.angle.mvplib.data.net.request.GetRequset;
import com.angle.mvplib.data.net.resposne.MvpResponse;
import com.angle.mvplib.util.ParamsUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mallshop.R;
import com.example.mallshop.databinding.FragmentHomeBinding;
import com.example.mallshop.home.activity.DetailGoodActivity;
import com.example.mallshop.home.adapter.HomeListAdapter;
import com.example.mallshop.home.entity.IndexData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.functions.Function;

public class HomeFragment extends BaseSmartFragment1<IndexData> {


    private FragmentHomeBinding root;

    @Override
    protected void initVeiw() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void bindingView(View view) {
        super.bindingView(view);
        root = FragmentHomeBinding.bind(view);

        GetRequset<IndexData> requset = new GetRequset<>(Constarct.URL.INDEX);
        requset.setParams(ParamsUtils.getCommonParams());
        requset.setType(IndexData.class);
        doRequest(requset);
    }

    @Override
    public void onResult(MvpResponse<IndexData> data) {
        if (data != null) {
            try {
                List<IndexData.IndexListData> list = getFunction().apply(data.getData());
                HomeListAdapter homeListAdapter = new HomeListAdapter(list, getContext());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                homeListAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                        switch (list.get(i).currentType) {
                            case 1:
                            case 2:
                            case 3:
                            case 6:
                            case 7:
                            case 9:
                                return 2;
                            default:
                                return 1;
                        }
                    }
                });
                root.rlvHome.setLayoutManager(gridLayoutManager);
                homeListAdapter.bindToRecyclerView(root.rlvHome);
                root.rlvHome.setAdapter(homeListAdapter);
                InitLinstener(list, homeListAdapter);

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private void InitLinstener(List<IndexData.IndexListData> list, HomeListAdapter homeListAdapter) {
        homeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent;
                switch (list.get(position).currentType) {
                    // 1 2 3 9
                    case IndexData.ITEM_TYPE_BRAND: {
                        intent = new Intent();
                        intent.putExtra("type", 0);
                        intent.putExtra("id", ((IndexData.BrandListBean) list.get(position).data).getId());
                        intent.setClass(getContext(), DetailGoodActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case IndexData.ITEM_TYPE_NEW: {
                        intent = new Intent();
                        intent.putExtra("id", ((IndexData.NewGoodsListBean) list.get(position).data).getId());
                        intent.setClass(getContext(), DetailGoodActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case IndexData.ITEM_TYPE_HOT: {
                        intent = new Intent();
                        intent.putExtra("id", ((IndexData.HotGoodsListBean) list.get(position).data).getId());
                        intent.setClass(getContext(), DetailGoodActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case IndexData.ITEM_TYPE_TOPIC: {
                    }
                    break;
                    case IndexData.ITEM_TYPE_CATEGORY: {
                        intent = new Intent();
                        intent.putExtra("id", ((IndexData.CategoryListBean.GoodsListBean) list.get(position).data).getId());
                        intent.setClass(getContext(), DetailGoodActivity.class);
                        startActivity(intent);
                    }
                    break;
                }
            }
        });
    }

    @NotNull
    private Function<IndexData, List<IndexData.IndexListData>> getFunction() {
        return new Function<IndexData, List<IndexData.IndexListData>>() {
            @Override
            public List<IndexData.IndexListData> apply(IndexData indexData) throws Throwable {
                List<IndexData.IndexListData> list = new ArrayList<>();
                //第一个对象的封装 Banner
                IndexData.IndexListData banner = new IndexData.IndexListData();
                banner.currentType = IndexData.ITEM_TYPE_BANNER;
                banner.data = indexData.getBanner();
                list.add(banner);

                //导航的封装
                IndexData.IndexListData tab = new IndexData.IndexListData();
                tab.currentType = IndexData.ITEM_TYPE_TAB;
                tab.data = indexData.getChannel();
                list.add(tab);
                //封装带top边距的标题
                IndexData.IndexListData title1 = new IndexData.IndexListData();
                title1.currentType = IndexData.ITEM_TYPE_TITLE_TOP;
                title1.data = "品牌制造商直供";
                list.add(title1);
                //封装品牌制造商直供的列表数据
                for (int i = 0; i < indexData.getBrandList().size(); i++) {
                    IndexData.IndexListData brand = new IndexData.IndexListData();
                    brand.currentType = IndexData.ITEM_TYPE_BRAND;
                    brand.data = indexData.getBrandList().get(i);
                    list.add(brand);
                }
                //新品首发标题
                IndexData.IndexListData title2 = new IndexData.IndexListData();
                title2.currentType = IndexData.ITEM_TYPE_TITLE;
                title2.data = "周一周四·新品首发";
                list.add(title2);
                //新品首发数据封装
                for (int i = 0; i < indexData.getNewGoodsList().size(); i++) {
                    IndexData.IndexListData newGood = new IndexData.IndexListData();
                    newGood.currentType = IndexData.ITEM_TYPE_NEW;
                    newGood.data = indexData.getNewGoodsList().get(i);
                    list.add(newGood);
                }
                //人气推荐
                IndexData.IndexListData title3 = new IndexData.IndexListData();
                title3.currentType = IndexData.ITEM_TYPE_TITLE_TOP;
                title3.data = "人气推荐";
                list.add(title3);
                //人气推荐数据
                for (int i = 0; i < indexData.getHotGoodsList().size(); i++) {
                    IndexData.IndexListData hot = new IndexData.IndexListData();
                    hot.currentType = IndexData.ITEM_TYPE_HOT;
                    hot.data = indexData.getHotGoodsList().get(i);
                    list.add(hot);
                }
                //专题精选
                IndexData.IndexListData title4 = new IndexData.IndexListData();
                title4.currentType = IndexData.ITEM_TYPE_TITLE_TOP;
                title4.data = "专题精选";
                list.add(title4);
                //专题精选数据
                IndexData.IndexListData topic = new IndexData.IndexListData();
                topic.currentType = IndexData.ITEM_TYPE_TOPIC;
                topic.data = indexData.getTopicList();
                list.add(topic);
                    /*for (int i=0; i<homeBean.getData().getTopicList().size(); i++){
                        HomeBean.HomeListBean brand = new HomeBean.HomeListBean();
                        brand.currentType = HomeBean.ITEM_TYPE_TOPIC;
                        brand.data = homeBean.getData().getTopicList().get(i);
                        list.add(brand);
                    }*/
                //解析商品数据
                for (IndexData.CategoryListBean item : indexData.getCategoryList()) {
                    //标题
                    IndexData.IndexListData title = new IndexData.IndexListData();
                    title.currentType = IndexData.ITEM_TYPE_TITLE_TOP;
                    title.data = item.getName();
                    list.add(title);
                    for (IndexData.CategoryListBean.GoodsListBean good : item.getGoodsList()) {
                        IndexData.IndexListData goodBean = new IndexData.IndexListData();
                        goodBean.currentType = IndexData.ITEM_TYPE_CATEGORY;
                        goodBean.data = good;
                        list.add(goodBean);
                    }
                }
                return list;
            }
        };
    }

    private static final String TAG = "HomeFragment";
}