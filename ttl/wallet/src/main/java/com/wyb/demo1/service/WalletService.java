package com.wyb.demo1.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.wyb.demo1.bean.User;
import com.wyb.demo1.bean.WalletTransaction;
import com.wyb.demo1.mapper.UserMapper;
import com.wyb.demo1.mapper.WalletTransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * @author wangyubo
 * @since 2023/10/11 2:01
 */

@Service
public class WalletService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WalletTransactionMapper walletTransactionMapper;

    public BigDecimal getUserBalance(Long userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getBalance();
        }
        return BigDecimal.ZERO;
    }

    @Transactional
    public boolean consume(Long userId, BigDecimal amount) {
        User user = userMapper.selectById(userId);
        if (user != null && user.getBalance().compareTo(amount) >= 0) {
            BigDecimal newBalance = user.getBalance().subtract(amount);
            user.setBalance(newBalance);

            userMapper.updateById(user);

            WalletTransaction transaction = new WalletTransaction();
            transaction.setTransactionType("consume");
            setTransation(userId, amount, transaction);

            return true;
        }
        return false;
    }

    @Transactional
    public void refund(Long userId, BigDecimal amount) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            BigDecimal newBalance = user.getBalance().add(amount);
            user.setBalance(newBalance);
            userMapper.updateById(user);

            WalletTransaction transaction = new WalletTransaction();
            transaction.setTransactionType("refund");
            setTransation(userId, amount, transaction);
        }
    }


    public List<WalletTransaction> getTransactionHistory(Long userId) {
        LambdaQueryWrapper<WalletTransaction> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WalletTransaction::getUserId,userId);
        return walletTransactionMapper.selectList(queryWrapper);
    }


    private void setTransation(Long userId, BigDecimal amount, WalletTransaction transaction) {
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setTransactionTime(new Timestamp(System.currentTimeMillis()));
        walletTransactionMapper.insert(transaction);
    }
}

