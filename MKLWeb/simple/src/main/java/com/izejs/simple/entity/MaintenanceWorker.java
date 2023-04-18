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
 * @since 2021-04-03
 */
public class MaintenanceWorker implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 维修人员id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 维修人员姓名
     */
    private String name;

    /**
     * 维修人员性别
     */
    private String sex;

    /**
     * 维修人员年龄
     */
    private Integer age;

    /**
     * 维修人员工号
     */
    private String workNumber;

    /**
     * 联系方式
     */
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MaintenanceWorker{" +
            "id=" + id +
            ", name=" + name +
            ", sex=" + sex +
            ", age=" + age +
            ", workNumber=" + workNumber +
        "}";
    }
}
