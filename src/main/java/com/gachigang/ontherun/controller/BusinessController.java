package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.service.BusinessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService businessService;

    @GetMapping
    public List<Business> getAllBusiness() {
        return businessService.getAllBusiness();
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
        log.info("User with id: {} was deleted", id);
        return ResponseEntity.ok().build();
    }
}

