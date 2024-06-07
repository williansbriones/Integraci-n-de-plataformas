package com.duoc.integracion_plataformas.service.iface;

import com.duoc.integracion_plataformas.dto.LoginDto;
import com.duoc.integracion_plataformas.dto.TokenLoginDto;
import com.duoc.integracion_plataformas.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface UserService {

  UserDto createUser(UserDto userDto);

  UserDto getUserById(Long id);

  List<UserDto> getAllUsers();

  Boolean existUser(Long id);

  ResponseEntity<UserDto> login(LoginDto loginDto);

  UserDto newUserNoRegister();
}
