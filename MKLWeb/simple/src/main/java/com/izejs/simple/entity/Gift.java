package com.izejs.simple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 积分礼品
 * </p>
 *
 * @author KunKa
 * @since 2021-04-26
 */
public class Gift implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 数量
     */
    private Integer price;

    /**
     * 商品说明
     */
    private String productDesc;

    /**
     * 礼品类型; 0.洗衣券
     */
    private Integer type;

    /**
     * 兑换所需积分
     */
    private BigDecimal score;

    /**
     * 可使用洗衣机品牌id
     */
    private Integer usedId;

    /**
     * 可使用洗衣机品牌名称
     */
    private String usedName;

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
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
    public Integer getUsedId() {
        return usedId;
    }

    public void setUsedId(Integer usedId) {
        this.usedId = usedId;
    }
    public String getUsedName() {
        return usedName;
    }

    public void setUsedName(String usedName) {
        this.usedName = usedName;
    }

    @Override
    public String toString() {
        return "Gift{" +
            "id=" + id +
            ", name=" + name +
            ", price=" + price +
            ", productDesc=" + productDesc +
            ", type=" + type +
            ", score=" + score +
            ", usedId=" + usedId +
            ", usedName=" + usedName +
        "}";
    }
}
