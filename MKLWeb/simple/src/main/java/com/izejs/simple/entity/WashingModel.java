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
 * @since 2021-04-15
 */
public class WashingModel implements Serializable {

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
     * 型号id
     */
    private Integer brandTypeId;

    /**
     * 型号名称
     */
    private String brandTypeName;

    /**
     * 模式名称
     */
    private String modelName;

    /**
     * 洗衣时长(单位:分钟)
     */
    private Integer runTime;

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
    public Integer getBrandTypeId() {
        return brandTypeId;
    }

    public void setBrandTypeId(Integer brandTypeId) {
        this.brandTypeId = brandTypeId;
    }
    public String getBrandTypeName() {
        return brandTypeName;
    }

    public void setBrandTypeName(String brandTypeName) {
        this.brandTypeName = brandTypeName;
    }
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    @Override
    public String toString() {
        return "WashingModel{" +
            "id=" + id +
            ", brandId=" + brandId +
            ", brandName=" + brandName +
            ", brandTypeId=" + brandTypeId +
            ", brandTypeName=" + brandTypeName +
            ", modelName=" + modelName +
            ", runTime=" + runTime +
        "}";
    }
}
