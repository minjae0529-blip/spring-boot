package com.korai.study03.domain.customer.dto;

import com.korai.study03.domain.customer.entity.Customer;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CustomerCreateRequest {
    //엔티티명 + 어떤 목적인지 + 요청인지, 응답인지

    private String name;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private String district;
    private String address;
    private String grade;
    private boolean marketingAgreed;


    public Customer toCustomer(){
        return new Customer(0, name, phone, email, birthDate, district, address, grade, marketingAgreed, LocalDateTime.now());
    }
}
