package com.duoc.integracion_plataformas.service;


import com.duoc.integracion_plataformas.dto.UserDto;
import com.duoc.integracion_plataformas.entity.UserEntity;
import com.duoc.integracion_plataformas.exeption.UserException;
import com.duoc.integracion_plataformas.repository.UserRepository;
import com.duoc.integracion_plataformas.service.iface.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.duoc.integracion_plataformas.enums.UserType.CLIENT;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDto createUser(UserDto userDto) {

    userRepository
        .findByEmail(userDto.email())
        .ifPresent(
            user -> {
              throw new UserException("409", HttpStatus.CONFLICT ,"Email already exists");
            });

    UserEntity userEntity =
        UserEntity.builder()
            .username(userDto.name())
            .lastName(userDto.lastName())
            .email(userDto.email())
            .password(userDto.password())
            .userType(CLIENT)
            .build();
    userRepository.save(userEntity);
    return UserDto.toUserDto(userEntity);
  }

  @Override
  public UserDto getUserById(Long id) {
    return this.userRepository.findById(id).orElseThrow(() -> new UserException("404", HttpStatus.BAD_REQUEST, "User not found")).toUserDto();
  }

  @Override
  public List<UserDto> getAllUsers() {
    log.info("Request of the alls users");
    return userRepository.findAll().stream().map(UserDto::toUserDto).toList();
    // return  new ArrayList<UserDto>();
  }

  @Override
  public Boolean existUser(Long id) {
    return userRepository.existsById(id);
  }
}
