package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.common.mapper.BusinessMapper;
import com.gachigang.ontherun.payload.business.request.BusinessRequest;
import com.gachigang.ontherun.payload.business.BusinessDto;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.service.BusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
    private final BusinessMapper businessMapper;

    /**
     * Retrieves a list of businesses with optional pagination.
     *
     * @param page The page number for pagination. Default is 0.
     * @param size The number of items per page. Default is 10.
     * @return A list of Business objects.
     */
    @GetMapping
    public ResponseEntity<List<BusinessDto>> getAllBusiness(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        List<Business> businesses = businessService.getAllBusiness(PageRequest.of(page, size));
        List<BusinessDto> response = businessMapper.businessesToBusinessDtos(businesses);
        return ResponseEntity.ok(response);
    }

    /**
     * Update an existing user.
     *
     * @param businessDto A {@link BusinessDto} object representing the updated user information.
     * @return A {@link ResponseEntity} containing the updated {@link BusinessDto} object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BusinessDto> updateBusiness(@RequestBody BusinessDto businessDto, @PathVariable Long id) {
        final Business business = businessService.updateBusiness(businessDto, id);
        final BusinessDto response = businessMapper.businessToBusinessDto(business);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Deletes the user with the given id.
     *
     * @param id The id of the business to delete.
     * @return a response with an HTTP status of OK if the business was successfully deleted,
     * or an HTTP status of NOT_FOUND if the business was not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<BusinessDto> deleteBusinessById(@PathVariable final Long id) {
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
    public ResponseEntity<List<BusinessDto>> findBusinessByOwners(@AuthenticationPrincipal User user) {
        List<Business> business = businessService.findBusinessByOwners(user);
        List<BusinessDto> response = businessMapper.businessesToBusinessDtos(business);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BusinessDto> createBusiness(BusinessRequest businessRequest, @AuthenticationPrincipal User user) {
        Business business = businessService.createBusiness(businessRequest, user);
        BusinessDto response = businessMapper.businessToBusinessDto(business);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}