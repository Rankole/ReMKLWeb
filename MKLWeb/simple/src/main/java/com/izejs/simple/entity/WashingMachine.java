package com.izejs.simple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author KunKa
 * @since 2021-04-13
 */
public class WashingMachine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
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
    private Integer typeId;

    /**
     * 型号名称
     */
    private String typeName;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 编号
     */
    private String number;

    /**
     * 状态 0:待使用; 1:已预约; 2:报修中; 3:使用中
     */
    private Integer status;

    /**
     * 预约人id
     */
    private Integer reservationUserId;

    /**
     * 预约人用户名
     */
    private String reservationUserName;

    /**
     * 预约时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime reservationDatetime;

    /**
     * 使用人id
     */
    private Integer usedUserId;

    /**
     * 使用人用户名
     */
    private String usedUserName;

    /**
     * 开始使用时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime usedDatetime;

    /**
     * 报修用户id
     */
    private Integer repairUserId;

    /**
     * 报修用户名
     */
    private String repairUserName;

    /**
     * 报修时间
     */
    private LocalDateTime repairDatetime;

    /**
     * 报修备注
     */
    private String repairRemark;

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
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getReservationUserId() {
        return reservationUserId;
    }

    public void setReservationUserId(Integer reservationUserId) {
        this.reservationUserId = reservationUserId;
    }
    public String getReservationUserName() {
        return reservationUserName;
    }

    public void setReservationUserName(String reservationUserName) {
        this.reservationUserName = reservationUserName;
    }
    public LocalDateTime getReservationDatetime() {
        return reservationDatetime;
    }

    public void setReservationDatetime(LocalDateTime reservationDatetime) {
        this.reservationDatetime = reservationDatetime;
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
    public LocalDateTime getUsedDatetime() {
        return usedDatetime;
    }

    public void setUsedDatetime(LocalDateTime usedDatetime) {
        this.usedDatetime = usedDatetime;
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
    public LocalDateTime getRepairDatetime() {
        return repairDatetime;
    }

    public void setRepairDatetime(LocalDateTime repairDatetime) {
        this.repairDatetime = repairDatetime;
    }
    public String getRepairRemark() {
        return repairRemark;
    }

    public void setRepairRemark(String repairRemark) {
        this.repairRemark = repairRemark;
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

    @Override
    public String toString() {
        return "WashingMachine{" +
            "id=" + id +
            ", brandId=" + brandId +
            ", brandName=" + brandName +
            ", typeId=" + typeId +
            ", typeName=" + typeName +
            ", price=" + price +
            ", number=" + number +
            ", status=" + status +
            ", reservationUserId=" + reservationUserId +
            ", reservationUserName=" + reservationUserName +
            ", reservationDatetime=" + reservationDatetime +
            ", usedUserId=" + usedUserId +
            ", usedUserName=" + usedUserName +
            ", usedDatetime=" + usedDatetime +
            ", repairUserId=" + repairUserId +
            ", repairUserName=" + repairUserName +
            ", repairDatetime=" + repairDatetime +
            ", repairRemark=" + repairRemark +
            ", workerId=" + workerId +
            ", workerName=" + workerName +
            ", workerPhone=" + workerPhone +
        "}";
    }
}
