package com.wyb.demo1.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author wangyubo
 * @since 2023/10/11 1:46
 */
@Data
public class WalletTransaction {
    @TableId(type = IdType.AUTO)
    private Long transactionId;
    private Long userId;
    private String transactionType;
    private BigDecimal amount;
    private Timestamp transactionTime;

    @Override
    public String toString() {
        return "WalletTransaction{" +
                "transactionId=" + transactionId +
                ", userId=" + userId +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", transactionTime=" + transactionTime +
                '}';
    }
}
