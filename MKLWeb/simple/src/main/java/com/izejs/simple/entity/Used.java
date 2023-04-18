package com.izejs.simple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author KunKa
 * @since 2021-04-15
 */
public class Used implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 洗衣机id
     */
    private Integer washingMachineId;

    /**
     * 洗衣机品牌
     */
    private String brand;

    /**
     * 洗衣机型号
     */
    private String brandType;

    /**
     * 洗衣机编号
     */
    private String washingMachineNumber;

    /**
     * 洗衣模式id
     */
    private Integer washingModelId;

    /**
     * 洗衣模式
     */
    private String washingModel;

    /**
     * 洗衣时长(单位:分钟)
     */
    private Integer washingTimes;

    /**
     * 使用时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime usedDatetime;

    /**
     * 运行时长(单位:分钟)
     */
    private Integer runTimes;

    /**
     * 状态; 1.运行中, 2.运行结束
     */
    private Integer status;

    /**
     * 使用者id
     */
    private Integer usedUserId;

    /**
     * 使用者用户名
     */
    private String usedUserName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getWashingMachineId() {
        return washingMachineId;
    }

    public void setWashingMachineId(Integer washingMachineId) {
        this.washingMachineId = washingMachineId;
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }
    public String getWashingMachineNumber() {
        return washingMachineNumber;
    }

    public void setWashingMachineNumber(String washingMachineNumber) {
        this.washingMachineNumber = washingMachineNumber;
    }
    public Integer getWashingModelId() {
        return washingModelId;
    }

    public void setWashingModelId(Integer washingModelId) {
        this.washingModelId = washingModelId;
    }
    public String getWashingModel() {
        return washingModel;
    }

    public void setWashingModel(String washingModel) {
        this.washingModel = washingModel;
    }
    public Integer getWashingTimes() {
        return washingTimes;
    }

    public void setWashingTimes(Integer washingTimes) {
        this.washingTimes = washingTimes;
    }
    public LocalDateTime getUsedDatetime() {
        return usedDatetime;
    }

    public void setUsedDatetime(LocalDateTime usedDatetime) {
        this.usedDatetime = usedDatetime;
    }
    public Integer getRunTimes() {
        return runTimes;
    }

    public void setRunTimes(Integer runTimes) {
        this.runTimes = runTimes;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getUsedUserId() {
        return usedUserId;
    }

    public void setUsedUserId(Integer usedUserId) {
        this.usedUserId = usedUserId;
    }
    public String getUsedUserName() {
        return usedUserName;
    }

    public void setUsedUserName(String usedUserName) {
        this.usedUserName = usedUserName;
    }

    @Override
    public String toString() {
        return "Used{" +
            "id=" + id +
            ", washingMachineId=" + washingMachineId +
            ", brand=" + brand +
            ", brandType=" + brandType +
            ", washingMachineNumber=" + washingMachineNumber +
            ", washingModelId=" + washingModelId +
            ", washingModel=" + washingModel +
            ", washingTimes=" + washingTimes +
            ", usedDatetime=" + usedDatetime +
            ", runTimes=" + runTimes +
            ", status=" + status +
            ", usedUserId=" + usedUserId +
            ", usedUserName=" + usedUserName +
        "}";
    }
}
