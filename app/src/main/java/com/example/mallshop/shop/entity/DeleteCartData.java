package com.example.mallshop.shop.entity;

import java.util.List;

public class DeleteCartData {
    private CartTotalBean cartTotal;
    private List<?> cartList;

    public CartTotalBean getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(CartTotalBean cartTotal) {
        this.cartTotal = cartTotal;
    }

    public List<?> getCartList() {
        return cartList;
    }

    public void setCartList(List<?> cartList) {
        this.cartList = cartList;
    }

    public static class CartTotalBean {
        /**
         * goodsCount : 0
         * goodsAmount : 0
         * checkedGoodsCount : 0
         * checkedGoodsAmount : 0
         */

        private int goodsCount;
        private int goodsAmount;
        private int checkedGoodsCount;
        private int checkedGoodsAmount;

        public int getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
        }

        public int getGoodsAmount() {
            return goodsAmount;
        }

        public void setGoodsAmount(int goodsAmount) {
            this.goodsAmount = goodsAmount;
        }

        public int getCheckedGoodsCount() {
            return checkedGoodsCount;
        }

        public void setCheckedGoodsCount(int checkedGoodsCount) {
            this.checkedGoodsCount = checkedGoodsCount;
        }

        public int getCheckedGoodsAmount() {
            return checkedGoodsAmount;
        }

        public void setCheckedGoodsAmount(int checkedGoodsAmount) {
            this.checkedGoodsAmount = checkedGoodsAmount;
        }
    }
}
