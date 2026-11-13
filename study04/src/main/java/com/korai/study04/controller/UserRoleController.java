package com.korai.study04.controller;

import com.korai.study04.repository.UserRepository;
import com.korai.study04.repository.UserRoleRepository;
import com.korai.study04.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

}
