package com.korai.study03.domain.customer.controller;

import com.korai.study03.domain.customer.Service.CustomerService;
import com.korai.study03.domain.customer.dto.CustomerCreateRequest;
import com.korai.study03.domain.customer.dto.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/customers")   // URL패턴 : /api/엔티티(복수형)
@RestController
public class CustomerController {
    //  컨트롤러 이름 규칙 : Entity명 + Controller
    // ㄷ엔티티마다 컨트롤러 만들어준다고 생각하면 됨

    //CRUD가 만들어져야 함 (CREATE, READ, UPDATE, DELETE)
    //C -> POST : 생성 createCustomer(), addCustomer(), registerCustomer() 이렇게 이름 지어짐
    //R -> GET : 조회
    //단건조회 : getCustomer(), findCustomer()
    //다건조회 : getCustomers(), listCustomers(), getCustomerList()
    //U -> PUT(전체수정), PATCH(부분 수정) : 수정
    //updateCustomer(), modifyCustomer()
    //D -> Delete : 삭제
    //deleteCustomer(), removerCustomer()

    @Autowired //자동으로 객체를 주입해주는거임
    private CustomerService customerService;

    @PostMapping//어떻게 하면 데이터를 받을수 있을까 고민해야 함 : 입력 매개변수, 리턴 출력
    public CustomerResponse create(@RequestBody CustomerCreateRequest request){
        System.out.println(request);
        CustomerResponse customerResponse = customerService.create(request);
        return customerResponse;
    }


}
