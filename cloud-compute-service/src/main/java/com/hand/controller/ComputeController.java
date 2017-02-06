package com.hand.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by DongFan on 2016/11/2.
 */
@RestController()
@RequestMapping("compute")
public class ComputeController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }

    @RequestMapping(value = "/add1", method = RequestMethod.POST)
    public Integer add1(@RequestBody Map<String, Integer> map) {
        return map.get("a") + map.get("b");
    }

    @RequestMapping("/test")
    public Object test() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
