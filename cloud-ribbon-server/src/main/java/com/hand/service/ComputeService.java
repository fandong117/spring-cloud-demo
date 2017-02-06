package com.hand.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by DongFan on 2016/11/2.
 */
@Service
public class ComputeService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String addService(Integer a, Integer b) {
        return restTemplate.getForEntity("http://CLOUD-COMPUTE-SERVICE/add?a=" + a + "&b=" + b, String.class).getBody();
    }

    public String addServiceFallback(Integer a, Integer b) {
        return "Add Service Error!";
    }
}
