/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.dto.mapper;

import java.util.List;

/**
 * Provides base generic interface for mapping operations between model and DTO objects.
 *
 * @param <M> Model type parameter.
 * @param <T> DTO type parameter.
 * @author Michał Gogolewski
 */
public interface ModelDTOMapper<M, T> {
    /**
     * Maps DTO object to model object.
     *
     * @param dto DTO object that will be mapped to model.
     * @return new mapped model from DTO.
     */
    M toModel(T dto);

    /**
     * Maps model object to DTO object.
     *
     * @param model model object that will be mapped to DTO.
     * @return new mapped DTO from model.
     */
    T toDTO(M model);

    /**
     * Maps DTOs list to models list.
     *
     * @param dtos DTOs list that will be mapped to models list.
     * @return new mapped models list from DTOs list.
     */
    List<M> toModelList(List<T> dtos);

    /**
     * Maps models list to DTOs list.
     *
     * @param models models list that will be mapped to DTOs list.
     * @return new mapped DTOs list from models list.
     */
    List<T> toDTOList(List<M> models);
}
