package com.lzy.springcloud.alibaba.service;

public interface StorageService {
    // 扣减库存
    void decrease(Long productId, Integer count);
}
