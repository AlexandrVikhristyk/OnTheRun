package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.payload.user.request.BusinessRequest;
import com.gachigang.ontherun.payload.user.request.UpdateBusinessRequest;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.service.BusinessService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for the business.
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/businesses")
@Slf4j
public class BusinessController {

    private final BusinessService businessService;

    /**
     * Endpoint for getting a list of businesses
     *
     * @return objects and an HTTP status code
     */
    @GetMapping
    public List<Business> getAllBusiness() {
        return businessService.getAllBusiness();
    }

    /**
     * Update an existing user.
     *
     * @param updateBusinessRequest A {@link UpdateBusinessRequest} object representing the updated user information.
     * @return A {@link ResponseEntity} containing the updated {@link UpdateBusinessRequest} object.
     */
    @PutMapping("/{id}")
    public Business updateBusiness(@RequestBody UpdateBusinessRequest updateBusinessRequest, @PathVariable Long id) {
        return businessService.updateBusiness(updateBusinessRequest, id);
    }

    /**
     * Deletes the user with the given id.
     *
     * @param id the id of the business to delete
     * @return a response with an HTTP status of OK if the business was successfully deleted,
     *      or an HTTP status of NOT_FOUND if the business was not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusinessById(@PathVariable Long id) {
        businessService.deleteBusinessById(id);
        log.info("Business with id: {} was deleted", id);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint for getting the owner of the business
     *
     * @return objects
     */
    @GetMapping("/owner")
    public List<Business> findBusinessByOwners(@AuthenticationPrincipal User user)  {
        return businessService.findBusinessByOwners(user);
    }

    @PostMapping
    public Business createBusiness (BusinessRequest businessRequest, @AuthenticationPrincipal User user){
        return businessService.createBusiness(businessRequest,user);
    }
}

