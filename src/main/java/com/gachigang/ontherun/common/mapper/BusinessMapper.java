package com.gachigang.ontherun.common.mapper;

import com.gachigang.ontherun.payload.business.BusinessDto;
import com.gachigang.ontherun.payload.business.request.BusinessRequest;
import com.gachigang.ontherun.persistence.entity.Business;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * This interface defines methods for mapping between the {@link Business} and {@link BusinessDto} classes.
 */
@Mapper
public interface BusinessMapper {

    /**
     * Maps a {@link BusinessDto} object to a {@link Business} object.
     *
     * @param businessDto The {@link BusinessDto} object to be mapped.
     * @return The resulting {@link Business} object.
     */
    @Mapping(target = "owners", ignore = true)
    Business businessDtoToBusiness(BusinessDto businessDto);

    /**
     * Maps a {@link Business} object to a {@link BusinessDto} object.
     *
     * @param business The {@link Business} object to be mapped.
     * @return The resulting {@link BusinessDto} object.
     */
    @Mapping(target = "owners", ignore = true)
    BusinessDto businessToBusinessDto(Business business);

    /**
     * Maps a {@link BusinessRequest} object to a {@link Business} object.
     *
     * @param businessRequest The {@link BusinessRequest} object to be mapped.
     * @return The resulting {@link Business} object.
     */
    Business businessRequestToBusiness(BusinessRequest businessRequest);

    /**
     * Maps a list of {@link Business} objects to a list of {@link BusinessDto} objects.
     *
     * @param businessList The list of {@link Business} objects to be mapped.
     * @return The resulting list of {@link BusinessDto} objects.
     */
    List<BusinessDto> businessesToBusinessDtos(List<Business> businessList);

    /**
     * Updates a {@link Business} object with values from a {@link BusinessDto} object.
     *
     * @param business      The {@link Business} object to be updated.
     * @param businessDto   The {@link BusinessDto} object with updated values.
     */
    @Mapping(target = "owners", ignore = true)
    void updateBusiness(@MappingTarget Business business, BusinessDto businessDto);
}