package com.korai.study04.controller;

import com.korai.study04.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

//@Component  이걸 등록하면 인스턴스가 자동 생성되는거임
@Controller
public class UserController {

    @Autowired  //자동 주입
    private UserService userService;

}
