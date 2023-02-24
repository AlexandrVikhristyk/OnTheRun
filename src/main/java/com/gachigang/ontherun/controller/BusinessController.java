package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.service.BusinessService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService businessService;

    @GetMapping
    public List<Business> getAllBusiness() {
        return businessService.getAllBusiness();
    }

    @PutMapping(value = "/business/{id}")
    public Business updateBusiness(@RequestBody Business business, @PathVariable(value = "id") Long id){
        return businessService.updateBusiness(business, id);
    }
}

