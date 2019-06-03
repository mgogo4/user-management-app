/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.mgogo.user_management_app.model.User;
import pl.mgogo.user_management_app.repository.UserRepository;

/**
 * Database initializer config populates configured database with initial data.
 * Starts with the application running with the 'initDB' profile.
 * Initial data is provided for application tests.
 *
 * @author Michał Gogolewski
 */
@Component
@Profile("initDB")
public class DatabaseInitializerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializerConfig.class);

    /**
     * User repository provides methods for manipulating user data in database.
     * Used for saving initial users in database.
     *
     * @see UserRepository
     */
    private final UserRepository repository;

    /**
     * Instantiates a new Database initializer config.
     *
     * @param repository injected user repository.
     */
    @Autowired
    public DatabaseInitializerConfig(final UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Populates database with initial data.
     * Creates 14 {@link User} entities and saves them in database with injected {@link UserRepository}.
     */
    @Bean
    public void populate() {
        LOGGER.info("Populating database with initial data.");
        repository.save(new User("John", "Doe", 5, 3400));
        repository.save(new User("John", "Powell", 12, 300));
        repository.save(new User("Johnny", "Smith", 4, 7234));
        repository.save(new User("Benedict", "Riggs", 5, 1234));
        repository.save(new User("Adrienne", "Kidd", 2, 17316));
        repository.save(new User("Jason", "Kidd", 3, 1342));
        repository.save(new User("Robert", "Winters", 1, 1500));
        repository.save(new User("Ursula", "Taylor", 10, 4293));
        repository.save(new User("Smith", "Taylor", 3, 524));
        repository.save(new User("Taylor", "Smith", 6, 1502));
        repository.save(new User("Warren", "Matthews", 12, 2390));
        repository.save(new User("Fiona", "Mcdonald", 0, 300));
        repository.save(new User("Donald", "Foreman", 2, 12000));
        repository.save(new User("Winter", "Powell", 1, 2700));
    }
}
