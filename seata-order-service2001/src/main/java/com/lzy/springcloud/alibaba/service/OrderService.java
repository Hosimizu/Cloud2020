package com.lzy.springcloud.alibaba.service;

import com.lzy.springcloud.alibaba.domain.Order;
import org.springframework.stereotype.Service;

public interface OrderService {
    void create(Order order);
}
