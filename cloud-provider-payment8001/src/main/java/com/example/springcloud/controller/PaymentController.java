package com.example.springcloud.controller;

import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.entities.Payment;
import com.example.springcloud.service.Paymentservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    public Paymentservice paymentservice;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment) {
        int result = paymentservice.create(payment);
        log.info("===插入结果" + result);
        if (result > 0) {
            return new CommonResult(200, "成功", result);
        } else {
            return new CommonResult(444, "失败", null);
        }
    }

    @GetMapping(value = "/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentservice.getPaymentById(id);
        log.info("===根据id查询"+paymentById);
        if (paymentById != null) {
            return new CommonResult(200, "succes", paymentById);
        } else {
            return new CommonResult(444, "没有对应记录,查询id"+id, null);
        }
    }


}
