package com.lzy.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //对atomicInteger的自增函数
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
            //判断当前对象和期望的expect（也就是前面的current）是否一致，如果一致则修改为next并返回true
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("*****第几次访问，次数next:" + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        //当总请求数为1时: 1 % 2 =1对应下标位置为1，则获得服务地址为127.0.0.1:8001
        //当总请求数位2时: 2 % 2 =0对应下标位置为0，则获得服务地址为127.0.0.1:8002
        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}
