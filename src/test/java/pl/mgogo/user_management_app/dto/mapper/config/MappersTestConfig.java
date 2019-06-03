/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.dto.mapper.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import pl.mgogo.user_management_app.dto.mapper.ModelDTOMapper;
import pl.mgogo.user_management_app.dto.mapper.UpdateUserDTOMapper;

/**
 * The type Mappers test helper configuration class.
 *
 * @author Michał Gogolewski
 */
@Configuration
@ComponentScan(lazyInit = true, basePackages = "pl.mgogo.user_management_app", useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ModelDTOMapper.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = UpdateUserDTOMapper.class)})
public class MappersTestConfig {

}
