package com.estsoft.springproject.user.controller;

import com.estsoft.springproject.user.domain.dto.AddUserRequest;
import com.estsoft.springproject.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST /user 요청 받고 회원가입 처리     -> /login 리디렉션
    @PostMapping("/user")
    public String save(@ModelAttribute AddUserRequest request) {
        userService.save(request);
        return "redirect:/login";
    }

}
