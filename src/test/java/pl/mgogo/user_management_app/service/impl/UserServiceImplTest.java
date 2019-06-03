/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.mgogo.user_management_app.dto.NewUserDTO;
import pl.mgogo.user_management_app.dto.UpdateUserDTO;
import pl.mgogo.user_management_app.dto.UserDTO;
import pl.mgogo.user_management_app.dto.mapper.NewUserDTOMapper;
import pl.mgogo.user_management_app.dto.mapper.UpdateUserDTOMapper;
import pl.mgogo.user_management_app.dto.mapper.UserDTOMapper;
import pl.mgogo.user_management_app.model.User;
import pl.mgogo.user_management_app.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * {@link UserServiceImpl} unit test class.
 *
 * @author Michał Gogolewski
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private final static Long DEFAULT_ID = 1L;
    private final static Long DEFAULT_ID_1 = 2L;
    private static final String DEFAULT_RSQL = "name==john";

    private static final Pageable DEFAULT_PAGE_REQUEST = PageRequest.of(0, 5);

    @Mock
    private UserRepository repository;

    @Mock
    private UserDTOMapper userDTOMapper;

    @Mock
    private NewUserDTOMapper newUserDTOMapper;

    @Mock
    private UpdateUserDTOMapper updateUserDTOMapper;

    @InjectMocks
    private UserServiceImpl service;

    @BeforeAll
    static void setUp() {
    }

    @Test
    public void findOneUserEntityByID_returnsCorrectEntity() {
        User user = new User();
        user.setId(DEFAULT_ID);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_ID);

        Mockito.doReturn(Optional.of(user)).when(repository).findById(DEFAULT_ID);
        Mockito.doReturn(userDTO).when(userDTOMapper).toDTO(user);
        Optional<UserDTO> result = service.findOne(DEFAULT_ID);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(DEFAULT_ID, result.get().getId());
    }

    @Test
    public void findOneUserEntityByID_returnsNull() {
        Mockito.doReturn(Optional.empty()).when(repository).findById(DEFAULT_ID);
        Optional<UserDTO> result = service.findOne(DEFAULT_ID);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void findOneUserEntityByNullID_returnsNull() {
        Mockito.doReturn(Optional.empty()).when(repository).findById(null);
        Optional<UserDTO> result = service.findOne(null);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void saveUserEntity_returnsCorrectEntity() {
        NewUserDTO newUserDTO = new NewUserDTO();
        User user = new User();

        User savedUser = new User();
        savedUser.setId(DEFAULT_ID);

        UserDTO savedUserDTO = new UserDTO();
        savedUserDTO.setId(DEFAULT_ID);

        Mockito.doReturn(user).when(newUserDTOMapper).toModel(newUserDTO);
        Mockito.doReturn(savedUser).when(repository).save(user);
        Mockito.doReturn(savedUserDTO).when(userDTOMapper).toDTO(savedUser);
        UserDTO result = service.create(newUserDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(DEFAULT_ID, result.getId());
    }

    @Test
    public void saveUserEntity_returnsNull() {
        NewUserDTO newUserDTO = new NewUserDTO();

        User user = new User();

        Mockito.doReturn(user).when(newUserDTOMapper).toModel(newUserDTO);
        Mockito.doReturn(null).when(repository).save(user);
        Mockito.doReturn(null).when(userDTOMapper).toDTO(null);
        UserDTO result = service.create(newUserDTO);

        Assertions.assertNull(result);
    }

    @Test
    public void saveNull_returnsNull() {
        Mockito.doReturn(null).when(newUserDTOMapper).toModel(null);
        Mockito.doReturn(null).when(repository).save(null);
        Mockito.doReturn(null).when(userDTOMapper).toDTO(null);
        UserDTO result = service.create(null);

        Assertions.assertNull(result);
    }

    @Test
    public void deleteUserEntityByID_userExists_returnsTrue() {
        Mockito.doReturn(true).when(repository).existsById(DEFAULT_ID);
        Mockito.doNothing().when(repository).deleteById(DEFAULT_ID);
        boolean result = service.delete(DEFAULT_ID);

        Assertions.assertTrue(result);
    }

    @Test
    public void deleteUserEntityByID_userNotExists_returnsFalse() {
        Mockito.doReturn(false).when(repository).existsById(DEFAULT_ID);
        boolean result = service.delete(DEFAULT_ID);

        Assertions.assertFalse(result);
    }

    @Test
    public void deleteUserEntityByNullID_repositoryDeleteByIDMethodOnceInvoked() {
        Mockito.doReturn(false).when(repository).existsById(null);
        boolean result = service.delete(null);

        Assertions.assertFalse(result);
    }

    @Test
    public void updateUserEntity_existingUserSuccessfullyUpdated_returnsCorrectEntity() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        User user = new User();

        User savedUser = new User();
        savedUser.setId(DEFAULT_ID);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_ID);

        Mockito.doReturn(Optional.of(user)).when(repository).findById(DEFAULT_ID);
        Mockito.doNothing().when(updateUserDTOMapper).updateModel(updateUserDTO, user);
        Mockito.doReturn(savedUser).when(repository).save(user);
        Mockito.doReturn(userDTO).when(userDTOMapper).toDTO(savedUser);
        Optional<UserDTO> result = service.update(DEFAULT_ID, updateUserDTO);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(DEFAULT_ID, result.get().getId());
    }

    @Test
    public void updateUserEntity_userNotExists_returnsNull() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();

        Mockito.doReturn(Optional.empty()).when(repository).findById(DEFAULT_ID);
        Optional<UserDTO> result = service.update(DEFAULT_ID, updateUserDTO);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void updateUserEntity_nullUserID_returnsNull() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();

        Mockito.doReturn(Optional.empty()).when(repository).findById(null);
        Optional<UserDTO> result = service.update(null, updateUserDTO);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void updateUserEntity_userExistsNullUpdateDTO_returnsNull() {
        User user = new User();

        User savedUser = new User();
        savedUser.setId(DEFAULT_ID);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_ID);

        Mockito.doReturn(Optional.of(user)).when(repository).findById(DEFAULT_ID);
        Mockito.doNothing().when(updateUserDTOMapper).updateModel(null, user);
        Mockito.doReturn(savedUser).when(repository).save(user);
        Mockito.doReturn(userDTO).when(userDTOMapper).toDTO(savedUser);
        Optional<UserDTO> result = service.update(DEFAULT_ID, null);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(DEFAULT_ID, result.get().getId());
    }

    @Test
    public void findAllMatchingUserEntities_returnsCorrectEntities() {
        User user = new User();
        User user_1 = new User();
        UserDTO userDTO_1 = new UserDTO();
        UserDTO userDTO = new UserDTO();

        user.setId(DEFAULT_ID);
        user_1.setId(DEFAULT_ID_1);
        userDTO.setId(DEFAULT_ID);
        userDTO_1.setId(DEFAULT_ID_1);

        List<User> users = Arrays.asList(user, user_1);
        Page<User> usersPage = new PageImpl<>(users, DEFAULT_PAGE_REQUEST, users.size());

        Mockito.doReturn(usersPage).when(repository).findAll(Mockito.any(), Mockito.eq(DEFAULT_PAGE_REQUEST));
        Mockito.doReturn(userDTO).when(userDTOMapper).toDTO(user);
        Mockito.doReturn(userDTO_1).when(userDTOMapper).toDTO(user_1);

        Page<UserDTO> result = service.findAll(DEFAULT_RSQL, DEFAULT_PAGE_REQUEST);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get().count(), users.size());
        Assertions.assertTrue(result.get().map(UserDTO::getId).anyMatch(f -> f.equals(DEFAULT_ID)));
        Assertions.assertTrue(result.get().map(UserDTO::getId).anyMatch(f -> f.equals(DEFAULT_ID_1)));
    }
}