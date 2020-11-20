package com.example.car;

/**
 * Created by ${cqc} on 2016/11/21.
 */

public class ShopBean {
    private boolean isShopSelect;
    private String shopName;
    private int shopId;

    public boolean getShopSelect() {
        return isShopSelect;
    }

    public void setShopSelect(boolean shopSelect) {
        isShopSelect = shopSelect;
    }



    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
}
