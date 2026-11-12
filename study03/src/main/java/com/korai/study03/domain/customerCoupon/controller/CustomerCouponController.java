package com.korai.study03.domain.customerCoupon.controller;

import com.korai.study03.domain.customerCoupon.dto.CustomerCouponResponse;
import com.korai.study03.domain.customerCoupon.entity.CustomerCoupon;
import com.korai.study03.domain.customerCoupon.mapper.CustomerCouponMapper;
import com.korai.study03.domain.customerCoupon.service.CustomerCouponService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers/{customerId}/coupons")
public class CustomerCouponController {

    @Autowired
    private CustomerCouponService customerCouponService;

    // 💡 컨트롤러는 Service만 호출해야 하므로 Mapper 직접 주입은 주석 처리합니다.

    @Autowired
    private CustomerCouponMapper customerCouponMapper;


    @GetMapping
    public List<CustomerCouponResponse> getAll(@PathVariable int customerId){
        return customerCouponService.getAll(customerId);
    }

    // 💡 주의: URL 괄호 오타 수정("/{customerCouponId}") & Service에 getId 메서드 구현 후 주석 해제하세요.

    @GetMapping("/{customerCouponId}")
    public CustomerCouponResponse getId(@PathVariable int customerId, @PathVariable int customerCouponId){
        return customerCouponService.getId(customerCouponId);
    }

}
