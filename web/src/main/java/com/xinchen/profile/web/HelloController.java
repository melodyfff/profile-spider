package com.xinchen.profile.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xinchen
 * @version 1.0
 * @date 29/04/2019 15:49
 */
@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @GetMapping("/greeting")
    public String hello(){
        return "hello.";
    }


}
