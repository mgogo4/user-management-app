/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.dto.mapper;

import org.mapstruct.Mapper;
import pl.mgogo.user_management_app.dto.NewUserDTO;
import pl.mgogo.user_management_app.model.User;


/**
 * The interface for mapping operations between {@link User} and {@link NewUserDTO}.
 * MapStruct generates its implementation.
 *
 * @author Michał Gogolewski
 * @see Mapper
 */
@Mapper(componentModel = "spring")
public interface NewUserDTOMapper extends ModelDTOMapper<User, NewUserDTO> {
}
