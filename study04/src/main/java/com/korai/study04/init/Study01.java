package com.korai.study04.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//CommandLineRunner가 상속된 클래스 안에서 @Component 로 어노테이션 되어 있음 메인에 있는 run을 호출하게 되는거임
//IOC가 자동으로 조립돼

@Component
public class Study01 implements CommandLineRunner  {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("실행!");
    }

}
