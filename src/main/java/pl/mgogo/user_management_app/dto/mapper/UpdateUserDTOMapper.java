/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.dto.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import pl.mgogo.user_management_app.dto.UpdateUserDTO;
import pl.mgogo.user_management_app.model.User;

/**
 * The interface for mapping update operations between {@link User} and {@link UpdateUserDTO}.
 * Provides mapping that updates original model with no empty DTO fields.
 * MapStruct generates its implementation.
 *
 * @author Michał Gogolewski
 * @see Mapper
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UpdateUserDTOMapper {
    /**
     * Map not blank string properties with prior checking of conditions to prevent from overwriting model fields
     * with blank DTO fields.
     *
     * @param dto   the dto that will be overwriting model fields.
     * @param model the model that will get fields overwritten.
     */
    @BeforeMapping
    default void mapNotBlankStringProperies(UpdateUserDTO dto, @MappingTarget User model) {
        if (StringUtils.isNotBlank(dto.getName())) {
            model.setName(dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getSurname())) {
            model.setSurname(dto.getSurname());
        }
    }

    /**
     * Updates model with DTO fields except name and surname fields.
     * For string fields (name and surname) needs to be ensured prior checking of conditions to prevent from
     * overwriting model fields with blank DTO fields.
     * Update mapping implementation is generated automatically by MapStruct.
     *
     * @param dto   the dto that will be overwriting model fields.
     * @param model the model that will get fields overwritten.
     */
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "surname", ignore = true)
    void updateModel(UpdateUserDTO dto, @MappingTarget User model);
}
