/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * Abstract base entity specifies common properties of application entities.
 *
 * @author Michał Gogolewski
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    /**
     * ID property is autogenerated entity primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    private Long id;

    /**
     * Entity property version used for database optimistic locking.
     * Ensures integrity when performing the merge operation and for optimistic concurrency control.
     *
     * @see Version
     */
    @Version
    private Integer version;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {

        return this.getClass().getSimpleName() + "{" +
                "id=" + id +
                '}';
    }
}