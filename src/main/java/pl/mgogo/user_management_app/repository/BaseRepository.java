/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.mgogo.user_management_app.model.AbstractEntity;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Michał Gogolewski
 */
@Repository
@NoRepositoryBean
interface BaseRepository<T extends AbstractEntity> extends PagingAndSortingRepository<T, Long>,
        JpaSpecificationExecutor<T> {
}

