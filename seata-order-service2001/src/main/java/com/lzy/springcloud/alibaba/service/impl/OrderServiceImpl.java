package com.lzy.springcloud.alibaba.service.impl;

import com.lzy.springcloud.alibaba.dao.OrderDao;
import com.lzy.springcloud.alibaba.domain.Order;
import com.lzy.springcloud.alibaba.service.AccountService;
import com.lzy.springcloud.alibaba.service.OrderService;
import com.lzy.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;
    @Override
    @GlobalTransactional
    public void create(Order order) {
        log.info("------>开始新建订单");
        //1 新建订单
        orderDao.create(order);
        //2 扣减库存
        log.info("------>订单微服务开始调用库存，做扣减count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("------>订单微服务结束调用库存，做扣减end");
        //3
        log.info("------>订单微服务开始调用账户，做扣减money");
        accountService.decrease(order.getProductId(),order.getMoney());
        log.info("------>订单微服务结束调用账户，做扣减end");
        //修改订单状态
        log.info("------>修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("------>修改订单状态结束");

        log.info("------>下订单结束了，O(∩_∩)O");

    }
}
