# 📂 IntelliJ 프로젝트 구조 그대로 따라하는 패키지별 실습 가이드

> **이 문서는 현재 인텔리제이의 좌측 프로젝트 탐색기(Project Explorer) 폴더 트리와 100% 동일하게 구성되었습니다.**  
> 새로운 도메인(기능)을 만들 때 **어느 폴더에 어떤 파일을 만들고 무슨 코드를 넣어야 하는지** 인텔리제이 구조 그대로 따라 하실 수 있습니다.

---

## 🌳 1. 인텔리제이 실제 패키지 구조 트리

```text
📁 study03/src/main/
├── 📁 java/com/korai/study03/
│   ├── 📄 Study03Application.java              (스프링 부트 실행 클래스)
│   └── 📁 domain/
│       ├── 📁 customer/                        [고객 도메인 - 등록 C]
│       │   ├── 📁 entity/Customer.java
│       │   ├── 📁 dto/
│       │   │   ├── CustomerCreateRequest.java
│       │   │   └── CustomerResponse.java
│       │   ├── 📁 mapper/CustomerMapper.java
│       │   ├── 📁 service/CustomerService.java
│       │   └── 📁 controller/CustomerController.java
│       │
│       ├── 📁 restaurant/                      [식당 도메인 - 등록 C]
│       │   ├── 📁 entity/Restaurant.java
│       │   ├── 📁 dto/
│       │   │   ├── RestaurantCreateRequest.java
│       │   │   └── RestaurantResponse.java
│       │   ├── 📁 mapper/RestaurantMapper.java
│       │   ├── 📁 service/RestaurantService.java
│       │   └── 📁 controller/RestaurantController.java
│       │
│       └── 📁 customerCoupon/                  [고객 쿠폰 도메인 - 조회 R]
│           ├── 📁 entity/CustomerCoupon.java
│           ├── 📁 dto/CustomerCouponResponse.java
│           ├── 📁 mapper/CustomerCouponMapper.java
│           ├── 📁 service/CustomerCouponService.java
│           └── 📁 controller/CustomerCouponController.java
│
└── 📁 resources/
    ├── 📄 application.yaml                     (DB 연결 및 MyBatis 설정)
    └── 📁 mapper/                              (MyBatis XML 쿼리문 모음)
        ├── 📄 customer-mapper.xml
        ├── 📄 restaurant-mapper.xml
        └── 📄 customer-coupon-mapper.xml
```

---

## 🚀 2. 기능 추가 시 폴더별 생성 순서 (Step 1 ~ Step 6)

새로운 도메인을 만들 때는 인텔리제이 폴더 트리에서 **아래 6개 폴더 순서대로 파일을 하나씩 생성**하면 에러 없이 완성됩니다:

```
[1] 📁 entity/       ➔ DB 테이블 원본 클래스 생성
[2] 📁 dto/          ➔ 요청용 XxxCreateRequest 생성 (+ toEntity)
[3] 📁 mapper/ & XML ➔ SQL 작성 인터페이스 및 XML 생성
[4] 📁 service/      ➔ 비즈니스 로직 작성
[5] 📁 dto/          ➔ 응답용 XxxResponse 생성 (+ toResponse)
[6] 📁 controller/   ➔ 주소 열고 최종 호출
```

---

## 📦 3. 패키지별 상세 역할 및 코드 구현 가이드

---

### [패키지 1] 📁 `entity/` (DB 테이블 원본)
* **어느 위치인가요?**: `com.korai.study03.domain.{기능}.entity`
* **무슨 역할을 하나요?**: MySQL 테이블의 컬럼들과 1:1로 똑같이 자바 필드를 선언하는 곳입니다.
* **필수 어노테이션**:
  * `@Data`: Getter, Setter 등 자동 생성
  * `@NoArgsConstructor`: MyBatis가 DB 조회 후 객체를 생성할 때 쓰는 기본 생성자
  * `@AllArgsConstructor`: 전체 필드를 채우는 생성자

#### 📄 예시: `Customer.java`
```java
package com.korai.study03.domain.customer.entity;

import com.korai.study03.domain.customer.dto.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int customerId;           // PK (DB auto_increment)
    private String name;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private String district;
    private String address;
    private String grade;
    private boolean marketingAgreed;
    private LocalDateTime joinedAt;

    // Entity ➔ Response DTO 변환 메서드 (5단계에서 완성)
    public CustomerResponse toCustomerResponse(){
        return new CustomerResponse(
            customerId, name, phone, email, birthDate, 
            district, address, grade, marketingAgreed, joinedAt
        );
    }
}
```

---

### [패키지 2] 📁 `dto/` (요청/응답 데이터 포장 상자)
* **어느 위치인가요?**: `com.korai.study03.domain.{기능}.dto`
* **무슨 역할을 하나요?**:
  * **`XxxCreateRequest`**: 손님(웹)이 입력해서 보낼 데이터만 담는 상자 (PK는 제외!)
  * **`XxxResponse`**: 처리가 끝난 뒤 손님(웹)에게 돌려줄 예쁜 응답 상자 (발급된 PK 포함!)

#### 📄 2-1. `CustomerCreateRequest.java` (입력 상자)
```java
package com.korai.study03.domain.customer.dto;

import com.korai.study03.domain.customer.entity.Customer;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CustomerCreateRequest {
    private String name;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private String district;
    private String address;
    private String grade;
    private boolean marketingAgreed;

    // Request DTO ➔ Entity 변환 메서드 (PK는 0으로, 가입일은 현재시간으로 초기화)
    public Customer toCustomer(){
        return new Customer(
            0, name, phone, email, birthDate, district, address, grade, marketingAgreed, LocalDateTime.now()
        );
    }
}
```

#### 📄 2-2. `CustomerResponse.java` (출력 상자)
```java
package com.korai.study03.domain.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomerResponse {
    private int id; // 화면에는 customerId 대신 깔끔하게 id로 응답
    private String name;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private String district;
    private String address;
    private String grade;
    private boolean marketingAgreed;
    private LocalDateTime joinedAt;
}
```

---

### [패키지 3] 📁 `mapper/` & 📁 `resources/mapper/` (SQL 창구)
* **어느 위치인가요?**:
  * 자바 인터페이스: `com.korai.study03.domain.{기능}.mapper`
  * XML 파일: `src/main/resources/mapper/`
* **무슨 역할을 하나요?**: DB와 직접 연결되어 SQL 쿼리문(INSERT, SELECT 등)을 실행합니다.

#### 📄 3-1. 자바 인터페이스: `CustomerMapper.java`
```java
package com.korai.study03.domain.customer.mapper;

import com.korai.study03.domain.customer.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper // 스프링과 MyBatis가 연결
public interface CustomerMapper {
    int insert(Customer customer); // XML의 <insert id="insert">와 이름 일치해야 함
}
```

#### 📄 3-2. XML 파일: `src/main/resources/mapper/customer-mapper.xml`
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- namespace: 반드시 연결할 자바 Mapper 인터페이스의 전체 패키지 경로 -->
<mapper namespace="com.korai.study03.domain.customer.mapper.CustomerMapper">

    <!-- useGeneratedKeys="true" : MySQL auto_increment 번호를 가져옴 -->
    <!-- keyProperty="customerId": 가져온 번호를 Customer 객체의 customerId 변수에 넣어줌 -->
    <insert id="insert" useGeneratedKeys="true" keyProperty="customerId" parameterType="com.korai.study03.domain.customer.entity.Customer">
        insert into customers
        values (
            default, 
            #{name}, 
            #{phone}, 
            #{email}, 
            #{birthDate}, 
            #{district},
            #{address}, 
            #{grade}, 
            #{marketingAgreed}, 
            #{joinedAt} 
        )
    </insert>

</mapper>
```

---

### [패키지 4] 📁 `service/` (비즈니스 로직 주방장)
* **어느 위치인가요?**: `com.korai.study03.domain.{기능}.service`
* **무슨 역할을 하나요?**:
  * DTO를 받아 Entity로 변환 (`request.toCustomer()`)
  * Mapper를 불러 DB 작업 실행 (`customerMapper.insert(customer)`)
  * 결과를 응답 DTO로 포장해 컨트롤러에 전달 (`customer.toCustomerResponse()`)
* **필수 어노테이션**:
  * `@Service`: 스프링 싱글톤 서비스 빈 등록
  * `@Autowired`: Mapper 객체 자동 주입

#### 📄 예시: `CustomerService.java`
```java
package com.korai.study03.domain.customer.Service;

import com.korai.study03.domain.customer.dto.CustomerCreateRequest;
import com.korai.study03.domain.customer.dto.CustomerResponse;
import com.korai.study03.domain.customer.entity.Customer;
import com.korai.study03.domain.customer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerResponse create(CustomerCreateRequest request){
        // 1. DTO -> Entity 변환 (customerId = 0)
        Customer customer = request.toCustomer();

        // 2. DB INSERT (auto_increment 된 ID가 customer 변수에 채워짐)
        // ⚠️ 주의: request.toCustomer()를 새로 호출하지 말고 만든 customer 변수를 그대로 넘겨야 함!
        customerMapper.insert(customer);

        // 3. 발급된 ID가 채워진 최종 Response DTO로 변환하여 리턴
        return customer.toCustomerResponse();
    }
}
```

---

### [패키지 5] 📁 `controller/` (요청 접수 창구)
* **어느 위치인가요?**: `com.korai.study03.domain.{기능}.controller`
* **무슨 역할을 하나요?**:
  * 웹 브라우저나 Swagger가 들어올 URL 주소 지정 (`@RequestMapping("/api/customers")`)
  * 클라이언트가 보낸 JSON 데이터 수신 (`@RequestBody`)
  * Service를 호출하고 결과 JSON을 리턴
* **필수 어노테이션**:
  * `@RestController`: `@Controller` + `@ResponseBody` (순수 JSON 데이터 응답)
  * `@RequestMapping("/api/엔티티복수형")`: 공통 URL 접두사

#### 📄 예시: `CustomerController.java`
```java
package com.korai.study03.domain.customer.controller;

import com.korai.study03.domain.customer.Service.CustomerService;
import com.korai.study03.domain.customer.dto.CustomerCreateRequest;
import com.korai.study03.domain.customer.dto.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/customers")   // URL 규칙: /api/엔티티(복수형)
@RestController                     // JSON 응답 컨트롤러
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping // [POST] /api/customers
    public CustomerResponse create(@RequestBody CustomerCreateRequest request){
        // 서비스 호출 후 결과를 그대로 브라우저에 리턴 (200 OK + JSON 응답)
        return customerService.create(request);
    }
}
```

---

## 🔍 4. 조회(SELECT) 기능을 추가할 때 패키지별 작성법 (CustomerCoupon 기준)

조회는 등록(INSERT)과 달리 입력 DTO(`Request`)가 없고 **URL 경로의 ID(`@PathVariable`)**를 받아옵니다:

1. **📁 `mapper/` (CustomerCouponMapper.java)**:
   ```java
   List<CustomerCoupon> findAll(@Param("customerId") int customerId);
   CustomerCoupon findById(@Param("customerCouponId") int customerCouponId);
   ```
2. **📁 `resources/mapper/` (customer-coupon-mapper.xml)**:
   ```xml
   <select id="findAll" resultType="com.korai.study03.domain.customerCoupon.entity.CustomerCoupon">
       select * from customer_coupons where customer_id = #{customerId}
   </select>
   ```
3. **📁 `service/` (CustomerCouponService.java)**:
   ```java
   public List<CustomerCouponResponse> getAll(int customerId){
       return customerCouponMapper.findAll(customerId)
               .stream()
               .map(CustomerCoupon::toCustomerCouponResponse) // 메서드 참조 (::)
               .toList();
   }
   ```
4. **📁 `controller/` (CustomerCouponController.java)**:
   ```java
   @GetMapping
   public List<CustomerCouponResponse> getAll(@PathVariable int customerId){
       return customerCouponService.getAll(customerId);
   }
   ```

---

## 💡 실습 중 이것만 체크하면 에러 100% 예방!

* ❌ **컨트롤러에서 서비스 호출할 때 NullPointerException 뜰 때**:
  👉 컨트롤러에 `@Autowired private XxxService xxxService;` 가 빠졌는지 확인!
* ❌ **MyBatis 실행 시 500 에러 (No getter...) 뜰 때**:
  👉 XML의 `#{변수명}`과 엔티티 필드 이름의 철자가 똑같은지 확인!
* ❌ **MyBatis SELECT 시 500 에러 (Cannot instantiate...) 뜰 때**:
  👉 엔티티 클래스 위에 **`@NoArgsConstructor` (기본 생성자)**가 빠졌는지 확인!
* ❌ **등록 후 번호가 0으로 나오는 문제**:
  👉 XML에 `useGeneratedKeys="true" keyProperty="id"`가 있는지 확인하고, 서비스에서 `customerMapper.insert(customer)`처럼 만든 변수를 그대로 넘겼는지 확인!
