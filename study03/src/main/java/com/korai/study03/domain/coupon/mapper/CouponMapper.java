package com.korai.study03.domain.coupon.mapper;

import com.korai.study03.domain.coupon.entity.Coupon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {
    int insert(Coupon coupon);
}
