package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.payload.user.request.UpdateBusinessRequest;
import com.gachigang.ontherun.payload.user.request.BusinessRequest;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import com.gachigang.ontherun.service.BusinessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for the business.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/business")
@Slf4j
public class BusinessController {

    private final BusinessService businessService;
    private final BusinessRepository businessRepository;

    @GetMapping
    public List<Business> getAllBusiness() {
        return businessService.getAllBusiness();
    }

    @PutMapping(value = "/business/{id}")
    public Business updateBusiness(@RequestBody UpdateBusinessRequest business, @PathVariable Long id) {
        return businessService.updateBusiness(business, id);
    }
    /**
     * Deletes the user with the given id.
     *
     * @param id the id of the business to delete
     * @return a response with an HTTP status of OK if the business was successfully deleted,
     *      or an HTTP status of NOT_FOUND if the business was not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusinessById(@PathVariable final Long id) {
        businessService.deleteBusinessById(id);
        log.info("Business with id: {} was deleted", id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/owner")
    public List<Business> findBusinessByOwners(@AuthenticationPrincipal User user)  {
        return businessService.findBusinessByOwners(user);
    }

    @PostMapping
    public Business createBusiness (BusinessRequest businessRequest, @AuthenticationPrincipal User user){
        return businessService.createBusiness(businessRequest,user);
    }
}

