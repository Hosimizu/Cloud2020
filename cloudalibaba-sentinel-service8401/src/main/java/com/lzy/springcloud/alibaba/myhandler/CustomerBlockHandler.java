package com.lzy.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lzy.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handlerException1(BlockException exception) {
        return new CommonResult(4444, "按客戶自定义, global handlerException----1");
    }
    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444, "按客戶自定义, global handlerException----2");
    }
}
