package com.izejs.simple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("monitor_model")
public class MonitorModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 电池编号
     */
    @TableField(value = "battery_id")
    private String batteryId;

    /**
     * 应用场景
     */
    @TableField(value = "scenario")
    private String scenario;

    /**
     * ETU名称
     */
    @TableField(value = "ETU_name")
    private String etuName;

    /**
     * 电池规格
     */
    @TableField(value = "battery_size")
    private String batterySize;

    /**
     * 数据间隔
     */
    @TableField(value = "Data_interval")
    private String dataInterval;

    /**
     * 预警规则
     */
    @TableField(value = "alarm_role")
    private String alarmRole;

    /**
     * 智能算法
     */
    @TableField(value = "algorithms")
    private String algorithms;

    /**
     * 是否选中
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 状态
     */
    @TableField(value = "state")
    private String state;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getBatteryId() { return batteryId; }

    public void setBatteryId(String batteryId) { this.batteryId = batteryId; }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getEtuName() { return etuName; }

    public void setEtuName(String etuName) { this.etuName = etuName; }

    public String getBatterySize() {
        return batterySize;
    }

    public void setBatterySize(String batterySize) {
        this.batterySize = batterySize;
    }

    public String getDataInterval() {
        return dataInterval;
    }

    public void setDataInterval(String dataInterval) {
        this.dataInterval = dataInterval;
    }

    public String getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(String algorithms) {
        this.algorithms = algorithms;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAlarmRole() { return alarmRole; }

    public void setAlarmRole(String alarmRole) { this.alarmRole = alarmRole; }

    @Override
    public String toString() {
        return "MonitorModel{" +
                "id=" + id +
                ", batteryId=" + batteryId +
                ", scenario=" + scenario +
                ", ETUName=" + etuName +
                ", batterySize=" + batterySize +
                ", dataInterval=" + dataInterval +
                ", alarmRole=" + alarmRole +
                ", algorithms=" + algorithms +
                ", status=" + status +
                ", state=" + state +
                "}";
    }

}
