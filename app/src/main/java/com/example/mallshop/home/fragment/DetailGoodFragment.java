package com.example.mallshop.home.fragment;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.angle.mvplib.base.view.BaseSmartFragment1;
import com.angle.mvplib.common.Constarct;
import com.angle.mvplib.data.net.request.GetRequset;
import com.angle.mvplib.data.net.resposne.MvpResponse;
import com.angle.mvplib.util.MvpUtils;
import com.bumptech.glide.Glide;
import com.example.mallshop.R;
import com.example.mallshop.base.CartCustomView;
import com.example.mallshop.databinding.FragmentDetailGoodBinding;
import com.example.mallshop.databinding.LayoutPopwindowGoodBinding;
import com.example.mallshop.home.adapter.RlvPicAdapter;
import com.example.mallshop.home.entity.GoodsDetailData;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DetailGoodFragment extends BaseSmartFragment1<GoodsDetailData> implements View.OnClickListener {

    private FragmentDetailGoodBinding root;
    private String html = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                $\n" +
            "            </body>\n" +
            "        </html>";
    private PopupWindow popupWindow;
    private String image;
    private int retail_price;

    @Override
    protected void initVeiw() {
        root.layoutNorms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopWindow();
            }
        });
        root.layoutCollect.setOnClickListener(this);
        root.layoutCart.setOnClickListener(this);
        root.txtAddChe.setOnClickListener(this);
    }

    /**
     * 设置弹框
     */
    private void showPopWindow() {
        if (popupWindow != null && popupWindow.isShowing()) {

        } else {
            LayoutPopwindowGoodBinding inflate = LayoutPopwindowGoodBinding.inflate(getLayoutInflater());
            int height = MvpUtils.dip2px(getContext(), 150);
            popupWindow = new PopupWindow(inflate.getRoot(), LinearLayout.LayoutParams.MATCH_PARENT, height);
            popupWindow.setContentView(inflate.getRoot());
            inflate.getRoot().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            inflate.txtClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
            });
            int[] pt = new int[2];
            //获取到的屏幕宽高(除开了当前组件的宽高）
            root.layoutBottom.getLocationInWindow(pt);
            // Display display = getWindowManager().getDefaultDisplay();
            // int activityheight = display.getHeight();
            popupWindow.showAtLocation(root.layoutBottom, Gravity.NO_GRAVITY, 0, pt[1] - height);
            inflate.layoutCartwindow.initView();
            inflate.layoutCartwindow.setOnClickListener(new CartCustomView.IClick() {
                @Override
                public void clickCB(int value) {
//                    currentNum = value;
                }
            });
            Glide.with(getContext()).load(image).into(inflate.imgGood);
            inflate.txtPrice.setText("价格：￥"+retail_price);
            inflate.txtClose.setText("请选择规格数量");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_good;
    }

    @Override
    protected void bindingView(View view) {
        super.bindingView(view);
        root = FragmentDetailGoodBinding.bind(view);

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", getActivity().getIntent().getIntExtra("id", 0));

        GetRequset<GoodsDetailData> requset = new GetRequset<>(Constarct.URL.GOODSDETAIL);
        requset.setParams(map);
        requset.setType(GoodsDetailData.class);
        doRequest(requset);
    }

    private GoodsDetailData goodsDetailData;

    @Override
    public void onResult(MvpResponse<GoodsDetailData> data) {
        if (data != null) {
            goodsDetailData = data.getData();
            Log.d(TAG, "onResult: " + data.getData().getInfo().toString());
            unBanner(data.getData().getGallery(), data.getData().getInfo().getPrimary_pic_url());
            unInfo(data.getData().getInfo(), data.getData().getBrand().getName());
            updateParameter(data.getData().getAttribute());
            unComment(data.getData().getComment());
            unIssue(data.getData().getIssue());
        }
    }

    private void unIssue(List<GoodsDetailData.IssueBean> issue) {
        if (issue.size() > 0) {

            root.tvIssue.setVisibility(View.VISIBLE);
            root.layoutIssue.setVisibility(View.VISIBLE);
            root.layoutIssue.removeAllViews();
            for (int i = 0; i < issue.size(); i++) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_issue, null);
                TextView tv_issue_question = view.findViewById(R.id.tv_issue_question);
                TextView tv_issue_answer = view.findViewById(R.id.tv_issue_answer);

                tv_issue_question.setText(issue.get(i).getQuestion());
                tv_issue_answer.setText(issue.get(i).getAnswer());

                root.layoutIssue.addView(view);
            }
        } else {
            root.tvIssue.setVisibility(View.GONE);
            root.layoutIssue.setVisibility(View.GONE);
        }
    }

    //评论
    private void unComment(GoodsDetailData.CommentBean comment) {
        if (comment.getCount() != 0) {
            root.layoutComment.setVisibility(View.VISIBLE);
            root.tvTime.setVisibility(View.VISIBLE);
            root.tvTitle.setVisibility(View.VISIBLE);
            root.rlvCount.setVisibility(View.VISIBLE);
            //评价个数
            root.tvCount.setText("评价(" + comment.getCount() + ")");
            //时间
            if (!TextUtils.isEmpty(comment.getData().getAdd_time())) {
                root.tvTime.setText(comment.getData().getAdd_time());
            }
            //内容
            if (!TextUtils.isEmpty(comment.getData().getContent())) {
                root.tvTitle.setText(comment.getData().getContent());
            }
            //图片
            if (comment.getData().getPic_list() != null) {
                List<GoodsDetailData.CommentBean.DataBean.PicListBean> pic_list = comment.getData().getPic_list();
                root.rlvCount.setLayoutManager(new GridLayoutManager(getContext(), 4));
                RlvPicAdapter rlvPicAdapter = new RlvPicAdapter(pic_list, getContext());
                root.rlvCount.setAdapter(rlvPicAdapter);
            }
        } else {
            root.layoutComment.setVisibility(View.GONE);
            root.tvTime.setVisibility(View.GONE);
            root.tvTitle.setVisibility(View.GONE);
            root.rlvCount.setVisibility(View.GONE);
        }
    }

    //商品参数
    private void updateParameter(List<GoodsDetailData.AttributeBean> attribute) {

        if (attribute.size() > 0 && attribute != null) {
            root.tvX.setVisibility(View.VISIBLE);
            root.layoutParameter.setVisibility(View.VISIBLE);

            root.layoutParameter.removeAllViews();

            for (int i = 0; i < attribute.size(); i++) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_parameter, null);
                TextView txt_parameter_name = view.findViewById(R.id.txt_parameter_name);
                TextView txt_parameter_value = view.findViewById(R.id.txt_parameter_value);
                txt_parameter_name.setText(attribute.get(i).getName());
                txt_parameter_value.setText(attribute.get(i).getValue());
                root.layoutParameter.addView(view);
            }
        } else {
            root.tvX.setVisibility(View.GONE);
            root.layoutParameter.setVisibility(View.GONE);
        }
    }

    //详细信息
    private void unInfo(GoodsDetailData.InfoBean info, String name) {
        //名字
        String infoName = info.getName();
        if (!TextUtils.isEmpty(infoName)) {
            root.txtName.setText(infoName);
        }

        //介绍
        String goods_brief = info.getGoods_brief();
        if (!TextUtils.isEmpty(goods_brief)) {
            root.txtDes.setText(goods_brief);
        }

        //价格
        retail_price = info.getRetail_price();
        root.txtPrice.setText("￥" + retail_price);

        //制造商
        if (!TextUtils.isEmpty(name)) {
            root.txtProduct.setVisibility(View.VISIBLE);
            root.txtProduct.setText(name + ">");
        } else {
            root.txtProduct.setVisibility(View.GONE);
        }
        //WebView
        if (!TextUtils.isEmpty(info.getGoods_desc())) {
            root.webView.setVisibility(View.VISIBLE);
            String h5 = info.getGoods_desc();

            html = html.replace("$", h5);

            root.webView.loadDataWithBaseURL("about:blank", html, "text/html", "utf-8", null);

        } else {
            root.webView.setVisibility(View.GONE);
        }

    }

    //Banner
    private void unBanner(List<GoodsDetailData.GalleryBean> gallery, String primary_pic_url) {
        List<String> images = new ArrayList<>();
        if (gallery.size() > 0) {
            for (int i = 0; i < gallery.size(); i++) {
                images.add(gallery.get(i).getImg_url());
            }
            root.banner.setImages(images).setImageLoader(new ImageBanner()).start();
        } else {
            images.add(primary_pic_url);
            root.banner.setImages(images).setImageLoader(new ImageBanner()).start();
        }
        if (images != null) {
            image = images.get(0);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_collect:
                break;
            case R.id.txt_addChe:
                addCart();
                break;
            case R.id.layout_cart:
                getActivity().finish();
                break;
        }
    }

    /**
     * 添加到购物车
     */
    private void addCart() {
        /*Boolean isLogin = SpUtils.getInstance().getBoolean("login");
        if (isLogin) {*/
        //判断当前购物车的操作
        if (popupWindow != null && popupWindow.isShowing()) {
            //添加到购物车的操作
            if (goodsDetailData.getProductList().size() > 0) {
                int goods_id = goodsDetailData.getProductList().get(0).getGoods_id();
                int id = goodsDetailData.getProductList().get(0).getId();
                popupWindow.dismiss();
                popupWindow = null;
            } else {
                Toast("没有产品数据");
            }
        } else {
            showPopWindow();
        }
        /*} else {
            Toast("没有登录");
        }*/
    }

    public class ImageBanner extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }

    private static final String TAG = "DetailGoodFragment";

}