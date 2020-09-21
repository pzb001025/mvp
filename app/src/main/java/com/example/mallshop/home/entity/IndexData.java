package com.example.mallshop.home.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Time: 2020/9/14  22:29
 * Author: Lenovo.pzb
 * Project: MallShop
 */
public class IndexData {

    public static final int ITEM_TYPE_BANNER = 1;//banner
    public static final int ITEM_TYPE_TAB = 2;//TAG
    public static final int ITEM_TYPE_TITLE = 3;//标题
    public static final int ITEM_TYPE_BRAND = 4; //品牌
    public static final int ITEM_TYPE_NEW = 5;//新品
    public static final int ITEM_TYPE_HOT = 6;//人气
    public static final int ITEM_TYPE_TOPIC = 7;//专题
    public static final int ITEM_TYPE_CATEGORY = 8;//类别
    public static final int ITEM_TYPE_TITLE_TOP = 9;//标题顶部就边距

    /**
     * 封装一个主页recyclerview公用数据对象
     * 用来代表主页所以显示的类容
     */
    public static class IndexListData<T> implements MultiItemEntity {
        public int currentType;//当前item的类型
        public T data;//记录当前从服务器获取到的数据对象

        @Override
        public int getItemType() {
            return currentType;
        }
    }


    private List<BannerBean> banner;
    private List<ChannelBean> channel;
    private List<NewGoodsListBean> newGoodsList;
    private List<HotGoodsListBean> hotGoodsList;
    private List<BrandListBean> brandList;
    private List<TopicListBean> topicList;
    private List<CategoryListBean> categoryList;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<ChannelBean> getChannel() {
        return channel;
    }

    public void setChannel(List<ChannelBean> channel) {
        this.channel = channel;
    }

    public List<NewGoodsListBean> getNewGoodsList() {
        return newGoodsList;
    }

    public void setNewGoodsList(List<NewGoodsListBean> newGoodsList) {
        this.newGoodsList = newGoodsList;
    }

    public List<HotGoodsListBean> getHotGoodsList() {
        return hotGoodsList;
    }

    public void setHotGoodsList(List<HotGoodsListBean> hotGoodsList) {
        this.hotGoodsList = hotGoodsList;
    }

    public List<BrandListBean> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandListBean> brandList) {
        this.brandList = brandList;
    }

    public List<TopicListBean> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicListBean> topicList) {
        this.topicList = topicList;
    }

    public List<CategoryListBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryListBean> categoryList) {
        this.categoryList = categoryList;
    }

    public static class BannerBean {
        /**
         * id : 1
         * ad_position_id : 1
         * media_type : 1
         * name : 合作 谁是你的菜
         * link :
         * image_url : http://yanxuan.nosdn.127.net/65091eebc48899298171c2eb6696fe27.jpg
         * content : 合作 谁是你的菜
         * end_time : 0
         * enabled : 1
         */

        private int id;
        private int ad_position_id;
        private int media_type;
        private String name;
        private String link;
        private String image_url;
        private String content;
        private int end_time;
        private int enabled;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAd_position_id() {
            return ad_position_id;
        }

        public void setAd_position_id(int ad_position_id) {
            this.ad_position_id = ad_position_id;
        }

        public int getMedia_type() {
            return media_type;
        }

        public void setMedia_type(int media_type) {
            this.media_type = media_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public int getEnabled() {
            return enabled;
        }

        public void setEnabled(int enabled) {
            this.enabled = enabled;
        }
    }

    public static class ChannelBean {
        /**
         * id : 1
         * name : 居家
         * url : /pages/category/category?id=1005000
         * icon_url : http://ac-3yr0g9cz.clouddn.com/c031ea3cf575f885cd1c.png
         * sort_order : 1
         */

        private int id;
        private String name;
        private String url;
        private String icon_url;
        private int sort_order;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public int getSort_order() {
            return sort_order;
        }

        public void setSort_order(int sort_order) {
            this.sort_order = sort_order;
        }
    }

    public static class NewGoodsListBean {
        /**
         * id : 1116011
         * name : 蔓越莓曲奇 200克
         * list_pic_url : http://yanxuan.nosdn.127.net/767b370d07f3973500db54900bcbd2a7.png
         * retail_price : 36
         */

        private int id;
        private String name;
        private String list_pic_url;
        private float retail_price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getList_pic_url() {
            return list_pic_url;
        }

        public void setList_pic_url(String list_pic_url) {
            this.list_pic_url = list_pic_url;
        }

        public float getRetail_price() {
            return retail_price;
        }

        public void setRetail_price(float retail_price) {
            this.retail_price = retail_price;
        }
    }

    public static class HotGoodsListBean {
        /**
         * id : 1006013
         * name : 双宫茧桑蚕丝被 空调被
         * list_pic_url : http://yanxuan.nosdn.127.net/583812520c68ca7995b6fac4c67ae2c7.png
         * retail_price : 699
         * goods_brief : 一级桑蚕丝，吸湿透气柔软
         */

        private int id;
        private String name;
        private String list_pic_url;
        private float retail_price;
        private String goods_brief;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getList_pic_url() {
            return list_pic_url;
        }

        public void setList_pic_url(String list_pic_url) {
            this.list_pic_url = list_pic_url;
        }

        public float getRetail_price() {
            return retail_price;
        }

        public void setRetail_price(float retail_price) {
            this.retail_price = retail_price;
        }

        public String getGoods_brief() {
            return goods_brief;
        }

        public void setGoods_brief(String goods_brief) {
            this.goods_brief = goods_brief;
        }
    }

    public static class BrandListBean {
        /**
         * id : 1026000
         * name : CK制造商
         * list_pic_url : http://yanxuan.nosdn.127.net/658f09b7ec522d31742b47b914d64338.png
         * simple_desc : 严选寻访Calvin Klein品牌的制造商，
         * 深入世界领带第一生产地，设计与品质并重，
         * 致力于给消费者带来优质典雅的服饰用品。
         * pic_url : http://yanxuan.nosdn.127.net/fb139c9f11a10deb41beeb42aaad488e.png
         * sort_order : 1
         * is_show : 1
         * floor_price : 39
         * app_list_pic_url : http://yanxuan.nosdn.127.net/658f09b7ec522d31742b47b914d64338.png
         * is_new : 1
         * new_pic_url : http://yanxuan.nosdn.127.net/76638fb8e6990aadf837ce761c3b7399.jpg
         * new_sort_order : 1
         */

        private int id;
        private String name;
        private String list_pic_url;
        private String simple_desc;
        private String pic_url;
        private int sort_order;
        private int is_show;
        private float floor_price;
        private String app_list_pic_url;
        private int is_new;
        private String new_pic_url;
        private int new_sort_order;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getList_pic_url() {
            return list_pic_url;
        }

        public void setList_pic_url(String list_pic_url) {
            this.list_pic_url = list_pic_url;
        }

        public String getSimple_desc() {
            return simple_desc;
        }

        public void setSimple_desc(String simple_desc) {
            this.simple_desc = simple_desc;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getSort_order() {
            return sort_order;
        }

        public void setSort_order(int sort_order) {
            this.sort_order = sort_order;
        }

        public int getIs_show() {
            return is_show;
        }

        public void setIs_show(int is_show) {
            this.is_show = is_show;
        }

        public float getFloor_price() {
            return floor_price;
        }

        public void setFloor_price(float floor_price) {
            this.floor_price = floor_price;
        }

        public String getApp_list_pic_url() {
            return app_list_pic_url;
        }

        public void setApp_list_pic_url(String app_list_pic_url) {
            this.app_list_pic_url = app_list_pic_url;
        }

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public String getNew_pic_url() {
            return new_pic_url;
        }

        public void setNew_pic_url(String new_pic_url) {
            this.new_pic_url = new_pic_url;
        }

        public int getNew_sort_order() {
            return new_sort_order;
        }

        public void setNew_sort_order(int new_sort_order) {
            this.new_sort_order = new_sort_order;
        }
    }

    public static class TopicListBean {
        /**
         * id : 314
         * title : 关爱他成长的每一个足迹
         * content : <img src="//yanxuan.nosdn.127.net/75c55a13fde5eb2bc2dd6813b4c565cc.jpg">
         * <img src="//yanxuan.nosdn.127.net/e27e1de2b271a28a21c10213b9df7e95.jpg">
         * <img src="//yanxuan.nosdn.127.net/9d413d1d28f753cb19096b533d53418d.jpg">
         * <img src="//yanxuan.nosdn.127.net/64b0f2f350969e9818a3b6c43c217325.jpg">
         * <img src="//yanxuan.nosdn.127.net/a668e6ae7f1fa45565c1eac221787570.jpg">
         * <img src="//yanxuan.nosdn.127.net/0d4004e19728f2707f08f4be79bbc774.jpg">
         * <img src="//yanxuan.nosdn.127.net/79ee021bbe97de7ecda691de6787241f.jpg">
         * avatar : https://yanxuan.nosdn.127.net/14943186689221563.png
         * item_pic_url : https://yanxuan.nosdn.127.net/14943267735961674.jpg
         * subtitle : 专业运动品牌同厂，毛毛虫鞋买二送一
         * topic_category_id : 2
         * price_info : 0
         * read_count : 6.4k
         * scene_pic_url : https://yanxuan.nosdn.127.net/14943267735961674.jpg
         * topic_template_id : 0
         * topic_tag_id : 0
         * sort_order : 1
         * is_show : 1
         */

        private int id;
        private String title;
        private String content;
        private String avatar;
        private String item_pic_url;
        private String subtitle;
        private int topic_category_id;
        private float price_info;
        private String read_count;
        private String scene_pic_url;
        private int topic_template_id;
        private int topic_tag_id;
        private int sort_order;
        private int is_show;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getItem_pic_url() {
            return item_pic_url;
        }

        public void setItem_pic_url(String item_pic_url) {
            this.item_pic_url = item_pic_url;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public int getTopic_category_id() {
            return topic_category_id;
        }

        public void setTopic_category_id(int topic_category_id) {
            this.topic_category_id = topic_category_id;
        }

        public float getPrice_info() {
            return price_info;
        }

        public void setPrice_info(float price_info) {
            this.price_info = price_info;
        }

        public String getRead_count() {
            return read_count;
        }

        public void setRead_count(String read_count) {
            this.read_count = read_count;
        }

        public String getScene_pic_url() {
            return scene_pic_url;
        }

        public void setScene_pic_url(String scene_pic_url) {
            this.scene_pic_url = scene_pic_url;
        }

        public int getTopic_template_id() {
            return topic_template_id;
        }

        public void setTopic_template_id(int topic_template_id) {
            this.topic_template_id = topic_template_id;
        }

        public int getTopic_tag_id() {
            return topic_tag_id;
        }

        public void setTopic_tag_id(int topic_tag_id) {
            this.topic_tag_id = topic_tag_id;
        }

        public int getSort_order() {
            return sort_order;
        }

        public void setSort_order(int sort_order) {
            this.sort_order = sort_order;
        }

        public int getIs_show() {
            return is_show;
        }

        public void setIs_show(int is_show) {
            this.is_show = is_show;
        }
    }

    public static class CategoryListBean {
        /**
         * id : 1005000
         * name : 居家
         * goodsList : [{"id":1009024,"name":"日式和风懒人沙发","list_pic_url":"http://yanxuan.nosdn.127.net/149dfa87a7324e184c5526ead81de9ad.png","retail_price":599},{"id":1015007,"name":"典雅美式全棉刺绣抱枕","list_pic_url":"http://yanxuan.nosdn.127.net/a2045004de8a6225289376ad54317fc8.png","retail_price":59},{"id":1020000,"name":"升级款记忆绵护椎腰靠","list_pic_url":"http://yanxuan.nosdn.127.net/819fdf1f635a694166bcfdd426416e8c.png","retail_price":79},{"id":1030001,"name":"160*230羊毛手工地毯","list_pic_url":"http://yanxuan.nosdn.127.net/88dc5d80c6f84102f003ecd69c86e1cf.png","retail_price":969},{"id":1030002,"name":"160*230羊毛圈绒枪刺地毯","list_pic_url":"http://yanxuan.nosdn.127.net/8b9328496990357033d4259fda250679.png","retail_price":899},{"id":1030003,"name":"160*230羊毛手工几何地毯","list_pic_url":"http://yanxuan.nosdn.127.net/1d1ab099dc0e254c15e57302e78e200b.png","retail_price":1469},{"id":1035006,"name":"全棉单面割绒浴室地垫","list_pic_url":"http://yanxuan.nosdn.127.net/ee92704f3b8323905b51fc647823e6e5.png","retail_price":56}]
         */

        private int id;
        private String name;
        private List<GoodsListBean> goodsList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean {
            /**
             * id : 1009024
             * name : 日式和风懒人沙发
             * list_pic_url : http://yanxuan.nosdn.127.net/149dfa87a7324e184c5526ead81de9ad.png
             * retail_price : 599
             */

            private int id;
            private String name;
            private String list_pic_url;
            private float retail_price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getList_pic_url() {
                return list_pic_url;
            }

            public void setList_pic_url(String list_pic_url) {
                this.list_pic_url = list_pic_url;
            }

            public float getRetail_price() {
                return retail_price;
            }

            public void setRetail_price(float retail_price) {
                this.retail_price = retail_price;
            }
        }
    }
}