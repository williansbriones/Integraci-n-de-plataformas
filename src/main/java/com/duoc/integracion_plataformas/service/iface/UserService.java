package com.duoc.integracion_plataformas.service.iface;

import com.duoc.integracion_plataformas.dto.UserDto;

import java.util.List;
import java.util.Optional;


public interface UserService {

  UserDto createUser(UserDto userDto);

  UserDto getUserById(Long id);

  List<UserDto> getAllUsers();

  Boolean existUser(Long id);


}
