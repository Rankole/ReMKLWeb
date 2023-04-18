package com.izejs.simple.vo;

import com.izejs.simple.entity.BrandType;

import java.util.List;

public class BrandTypeVO {

    private Integer id;

    private Integer brandId;

    private String brandName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<BrandType> getBrandTypes() {
        return brandTypes;
    }

    public void setBrandTypes(List<BrandType> brandTypes) {
        this.brandTypes = brandTypes;
    }

    private List<BrandType> brandTypes;
}
