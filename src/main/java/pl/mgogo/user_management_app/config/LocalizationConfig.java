/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Localization config configures beans used in application localization operations.
 *
 * @author Michał Gogolewski
 */
@Configuration
public class LocalizationConfig {
    /**
     * Creates and configures Validator local validator factory bean.
     * Configures bean for using provided localization message source - messages Resource Bundle.
     * Provides translations for entities and model validation messages.
     *
     * @param messageSource injected localization message source.
     * @return configured local validator factory bean.
     */
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}