/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.service.impl;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mgogo.user_management_app.dto.NewUserDTO;
import pl.mgogo.user_management_app.dto.UpdateUserDTO;
import pl.mgogo.user_management_app.dto.UserDTO;
import pl.mgogo.user_management_app.dto.mapper.NewUserDTOMapper;
import pl.mgogo.user_management_app.dto.mapper.UpdateUserDTOMapper;
import pl.mgogo.user_management_app.dto.mapper.UserDTOMapper;
import pl.mgogo.user_management_app.model.User;
import pl.mgogo.user_management_app.repository.UserRepository;
import pl.mgogo.user_management_app.service.UserService;
import pl.mgogo.user_management_app.util.rsql.CustomRsqlVisitor;

import java.util.Optional;

/**
 * Service Implementation of {@link UserService} interface for managing
 * {@link pl.mgogo.user_management_app.model.User} entities.
 * Accepts and returns DTO maps them to entities to perform database operations.
 *
 * @author Michał Gogolewski
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    /**
     * @see UserRepository
     */
    private final UserRepository repository;

    /**
     * @see UserDTOMapper
     */
    private final UserDTOMapper userDTOMapper;

    /**
     * @see NewUserDTOMapper
     */
    private final NewUserDTOMapper newUserDTOMapper;

    /**
     * @see UpdateUserDTOMapper
     */
    private final UpdateUserDTOMapper updateUserDTOMapper;


    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final UserDTOMapper userDTOMapper,
                           final NewUserDTOMapper newUserDTOMapper, final UpdateUserDTOMapper updateUserDTOMapper) {
        this.repository = userRepository;
        this.userDTOMapper = userDTOMapper;
        this.newUserDTOMapper = newUserDTOMapper;
        this.updateUserDTOMapper = updateUserDTOMapper;
    }

    @Override
    public UserDTO create(NewUserDTO newUserDTO) {
        return userDTOMapper.toDTO(repository.save(newUserDTOMapper.toModel(newUserDTO)));
    }

    @Override
    public Optional<UserDTO> update(Long id, UpdateUserDTO updateUserDTO) {
        return repository.findById(id)
                .map(user -> {
                    updateUserDTOMapper.updateModel(updateUserDTO, user);
                    return userDTOMapper.toDTO(repository.save(user));
                });
    }

    @Override
    public Optional<UserDTO> findOne(Long id) {
        return repository.findById(id).map(userDTOMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(String rsql, Pageable pageable) {
        return repository.findAll(buildSpecification(rsql), pageable).map(userDTOMapper::toDTO);
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Builds specification from given string formatted as rsql.
     *
     * @param rsql the rsql string.
     * @return the specification from given rsql string.
     */
    private Specification<User> buildSpecification(String rsql) {
        Specification<User> specification = Specification.where(null);
        if (StringUtils.isNotBlank(rsql)) {
            final Node rootNode = new RSQLParser().parse(rsql);
            specification.and(rootNode.accept(new CustomRsqlVisitor<>()));
        }
        return specification;
    }
}
