package com.wyb.demo1.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wangyubo
 * @since 2023/10/11 1:45
 */
@Data
public class User {
    @TableId
    private Long userId;
    private String username;
    private BigDecimal balance;
}
