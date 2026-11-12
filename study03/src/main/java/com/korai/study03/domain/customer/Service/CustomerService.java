package com.korai.study03.domain.customer.Service;

import com.korai.study03.domain.customer.dto.CustomerCreateRequest;
import com.korai.study03.domain.customer.dto.CustomerResponse;
import com.korai.study03.domain.customer.entity.Customer;
import com.korai.study03.domain.customer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    //싱글톤으로 만들어주는 어노테이션 -> 싱글톤 코드를 저 어노테이션으로 대체 함
public class CustomerService {

    @Autowired
   private CustomerMapper customerMapper;

    public CustomerResponse create(CustomerCreateRequest request){

        Customer customer = request.toCustomer();
        System.out.println("insert 전");
        System.out.println(customer);

        customerMapper.insert(customer);

        System.out.println("insert 후");
        System.out.println(customer);

        return customer.toCustomerResponse();
    }


}
