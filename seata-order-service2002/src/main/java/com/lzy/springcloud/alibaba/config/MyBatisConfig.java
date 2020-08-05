package com.lzy.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.lzy.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
