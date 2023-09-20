package com.mz.qualifier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    @Qualifier("Honda")
    private Car car;

    @Autowired
    private MyService service;

    @GetMapping("/")
    public String getCar() {
        return service.getService() + " / " + car.getBrand();
    }

}
