package com.study.r4a226.webportal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalController {
  @GetMapping("/index")
  public String index() {
    return "/index";
  }

  @GetMapping("/")
  public String login() {
    return "/login";
  }

}
