package com.izejs.simple.dto;

/**
 * <p>
 *
 * </p>
 *
 * @author KunKa
 * @since 2021-04-03
 */
 public class PaymentInfoDTO{

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private String type;

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }
            private String paymentExplain;

    public String getPaymentExplain(){
        return this.paymentExplain;
    }

    public void setPaymentExplain(String explain){
        this.paymentExplain = explain;
    }
 }

