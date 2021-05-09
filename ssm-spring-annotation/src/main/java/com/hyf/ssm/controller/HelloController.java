package com.hyf.ssm.controller;

import com.hyf.ssm.pojo.Hello;
import com.hyf.ssm.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baB_hyf
 * @date 2020/05/17
 */
@RestController
public class HelloController {

    @Autowired
    private IHelloService helloService;

    @GetMapping("hello")
    public ResponseEntity<Hello> hello() {
        return ResponseEntity.ok(helloService.hello(1));
    }
}
