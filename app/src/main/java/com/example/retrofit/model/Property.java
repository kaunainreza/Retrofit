package com.example.retrofit.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Property {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("price")
    @Expose
    private Long price;
    @SerializedName("img_src")
    @Expose
    private String imgSrc;
    @SerializedName("isSelected")
    @Expose
    private Boolean isSelected;

    public Property(String id, String type, String imgSrc, Long price, Boolean isSelected) {
        this.id = id;
        this.type = type;
        this.imgSrc  = imgSrc;
        this.price = price;
        this.isSelected =isSelected;

    }

    public Property() {

    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }
    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

}