package com.korai.study03.domain.coupon.entity;

import com.korai.study03.domain.coupon.dto.CouponResponse;
import jakarta.validation.constraints.NegativeOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    private int id;
    private String code;
    private String name;
    private String discountType;
    private int discountValue;
    private int maxDiscount;
    private int minOrderAmount;
    private LocalDate validDays;
    private boolean isActive;
    private LocalDateTime createdAt;

    public CouponResponse toCouponResponse(){
        return new CouponResponse(
                id,
                code,
                name,
                discountType,
                discountValue,
                maxDiscount,
                minOrderAmount,
                validDays,
                isActive,
                createdAt
        );

    }
}
