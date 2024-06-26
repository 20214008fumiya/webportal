package com.study.r4a226.webportal.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
  @GetMapping("user")
  public String getUserList(@RequestParam String param) {
    // UserEntity userEntity = userEntity.selectAll();
    return "user/list";
  }
}
