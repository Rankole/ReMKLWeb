package com.izejs.simple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 报修记录表
 * </p>
 *
 * @author KunKa
 * @since 2021-04-15
 */
public class Repair implements Serializable {

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
     * 维修工id
     */
    private Integer workerId;

    /**
     * 维修工姓名
     */
    private String workerName;

    /**
     * 维修工电话
     */
    private String workerPhone;

    /**
     * 报修时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime repairDateime;

    /**
     * 处理时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime solveDatetime;

    /**
     * 状态(0.待维修, 1.已维修)
     */
    private Integer status;

    private String repairRemark;

    private Integer repairUserId;

    private String repairUserName;

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
    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }
    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }
    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }
    public LocalDateTime getRepairDateime() {
        return repairDateime;
    }

    public void setRepairDateime(LocalDateTime repairDateime) {
        this.repairDateime = repairDateime;
    }
    public LocalDateTime getSolveDatetime() {
        return solveDatetime;
    }

    public void setSolveDatetime(LocalDateTime solveDatetime) {
        this.solveDatetime = solveDatetime;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRepairRemark() {
        return repairRemark;
    }

    public void setRepairRemark(String repairRemark) {
        this.repairRemark = repairRemark;
    }

    public Integer getRepairUserId() {
        return repairUserId;
    }

    public void setRepairUserId(Integer repairUserId) {
        this.repairUserId = repairUserId;
    }

    public String getRepairUserName() {
        return repairUserName;
    }

    public void setRepairUserName(String repairUserName) {
        this.repairUserName = repairUserName;
    }

    @Override
    public String toString() {
        return "Repair{" +
            "id=" + id +
            ", washingMachineId=" + washingMachineId +
            ", brand=" + brand +
            ", brandType=" + brandType +
            ", washingMachineNumber=" + washingMachineNumber +
            ", workerId=" + workerId +
            ", workerName=" + workerName +
            ", workerPhone=" + workerPhone +
            ", repairDateime=" + repairDateime +
            ", solveDatetime=" + solveDatetime +
            ", status=" + status +
        "}";
    }
}
