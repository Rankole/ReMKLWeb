package com.izejs.simple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author KunKa
 * @since 2021-04-27
 */
public class UserGift implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    private String userName;

    /**
     * 商品id
     */
    private Integer giftId;

    /**
     * 商品名称
     */
    private String giftName;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 商品说明
     */
    private String giftDesc;

    /**
     * 兑换所需积分
     */
    private BigDecimal giftScore;

    /**
     * 可以使用品牌id
     */
    private Integer giftUsedId;

    /**
     * 可以使用品牌名称
     */
    private String giftUsedName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }
    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getGiftDesc() {
        return giftDesc;
    }

    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc;
    }
    public BigDecimal getGiftScore() {
        return giftScore;
    }

    public void setGiftScore(BigDecimal giftScore) {
        this.giftScore = giftScore;
    }
    public Integer getGiftUsedId() {
        return giftUsedId;
    }

    public void setGiftUsedId(Integer giftUsedId) {
        this.giftUsedId = giftUsedId;
    }
    public String getGiftUsedName() {
        return giftUsedName;
    }

    public void setGiftUsedName(String giftUsedName) {
        this.giftUsedName = giftUsedName;
    }

    @Override
    public String toString() {
        return "UserGift{" +
            "id=" + id +
            ", userId=" + userId +
            ", giftId=" + giftId +
            ", giftName=" + giftName +
            ", amount=" + amount +
            ", giftDesc=" + giftDesc +
            ", giftScore=" + giftScore +
            ", giftUsedId=" + giftUsedId +
            ", giftUsedName=" + giftUsedName +
        "}";
    }
}
