package com.tui.proof.ws.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
public class FooController {

  @GetMapping("/order")
  public String order() {
	  log.info("Foo controller - order");
	  return "order";
  }
  @GetMapping("/search")
  public String search() {
	  log.info("Foo controller - search");
	  return "search";
  }
}
