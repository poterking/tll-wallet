package com.wyb.demo1.controller;



import com.wyb.demo1.bean.WalletTransaction;
import com.wyb.demo1.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wangyubo
 * @since 2023/10/11 2:06
 */

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/balance")
    public BigDecimal getUserBalance(@RequestParam Long userId) {

        return walletService.getUserBalance(userId);
    }

   @GetMapping("/consume")
    public ResponseEntity<String> consume(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        if (walletService.consume(userId, amount)) {
            return ResponseEntity.ok("消费成功");
        } else {
            return ResponseEntity.badRequest().body("余额不足");
        }
    }

    @GetMapping("/refund")
    public ResponseEntity<String> refund(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        walletService.refund(userId, amount);
        return ResponseEntity.ok("退款成功");
    }

    @GetMapping("/transactions")
    public List<WalletTransaction> getTransactionHistory(@RequestParam Long userId) {
        return walletService.getTransactionHistory(userId);
    }
}

