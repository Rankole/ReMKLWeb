package com.izejs.simple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author KunKa
 * @since 2021-04-13
 */
public class BrandType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 型号名称
     */
    private String typeName;

    /**
     * 型号简介
     */
    private String typeIns;

    /**
     * 图片路径
     */
    private String imgPath;

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
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getTypeIns() {
        return typeIns;
    }

    public void setTypeIns(String typeIns) {
        this.typeIns = typeIns;
    }
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "BrandType{" +
            "id=" + id +
            ", brandId=" + brandId +
            ", brandName=" + brandName +
            ", typeName=" + typeName +
            ", typeIns=" + typeIns +
            ", imgPath=" + imgPath +
        "}";
    }
}
