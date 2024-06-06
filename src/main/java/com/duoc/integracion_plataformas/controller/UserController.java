package com.duoc.integracion_plataformas.controller;


import com.duoc.integracion_plataformas.dto.FlowResponseDto;
import com.duoc.integracion_plataformas.dto.UserDto;
import com.duoc.integracion_plataformas.exeption.UserException;
import com.duoc.integracion_plataformas.service.iface.FlowService;
import com.duoc.integracion_plataformas.service.iface.UserService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;
  private final FlowService flowService;

  @PostMapping("/create")
  public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
    UserDto user = userService.createUser(userDto);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/get")
  public ResponseEntity<UserDto> getUser(@RequestParam Long id) {
    if (id == null || id == 0) {
      throw new UserException("302", HttpStatus.BAD_REQUEST, "Id is required", LocalDate.now());
    }
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @GetMapping("/get/all")
  public ResponseEntity<List<UserDto>> getAllUsers() {
    List<UserDto> users = userService.getAllUsers();
    if (users.isEmpty()) {
      throw new UserException("303", HttpStatus.NOT_FOUND, "No users found", LocalDate.now());
    }
    return ResponseEntity.ok(users);
  }

  @GetMapping("exist/user")
  public ResponseEntity<Boolean> existUser(Long id) {
    Boolean existUser = userService.existUser(id);
    return new ResponseEntity<>(existUser, HttpStatus.OK);
  }




  //test de flow service
  @SneakyThrows
  @PostMapping("get/test")
  public FlowResponseDto getTest() {
    FlowResponseDto x = flowService.CreateOrder(5001, "wi.briones@duocuc.cl");
    return x.toBuilder().url(x.url() + "?token=" + x.token()).build();
    // public LinkedMultiValueMap<String, String> param(){
    //  return flowService.param();
  }
}
