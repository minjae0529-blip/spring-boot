package com.korai.study04.service;

import com.korai.study04.repository.UserRepository;
import com.korai.study04.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserRoleService {

    private UserRoleRepository userRoleRepository;
}
