package com.example.lazyfragment;

public class DataBean {
    private int image;
    private String img;

    public String getImg() {
        return img;
    }

    public DataBean(String img) {
        this.img = img;
    }

    public DataBean(int image) {
        this.image = image;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
