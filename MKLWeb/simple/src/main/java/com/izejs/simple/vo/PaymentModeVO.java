package com.izejs.simple.vo;

import com.izejs.simple.entity.UserGift;

import java.math.BigDecimal;
import java.util.List;

public class PaymentModeVO {

    private BigDecimal balance;
    private List<UserGift> userGiftList;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<UserGift> getUserGiftList() {
        return userGiftList;
    }

    public void setUserGiftList(List<UserGift> userGiftList) {
        this.userGiftList = userGiftList;
    }
}
