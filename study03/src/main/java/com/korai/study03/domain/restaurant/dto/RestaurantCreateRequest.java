package com.korai.study03.domain.restaurant.dto;

import com.korai.study03.domain.customer.entity.Customer;
import com.korai.study03.domain.restaurant.entity.Restaurant;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RestaurantCreateRequest {
    private String name;
    private String category;
    private String district;
    private String address;
    private String phone;
    private int minOrderAmount;
    private int deliveryFee;
    private String openTime;
    private String closeTime;
    private LocalDateTime openedAt;
    private boolean isOpen;    //json 특징 중 하나로 is, ed있으면 삭제 해서 표현함

    public Restaurant toRestaurant(){
        return new Restaurant(0, name, category, district, address, phone, minOrderAmount, deliveryFee, openTime, closeTime, openedAt, isOpen);
    }
    
}
