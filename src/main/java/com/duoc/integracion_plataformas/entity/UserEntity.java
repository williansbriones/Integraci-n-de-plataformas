package com.duoc.integracion_plataformas.entity;

import com.duoc.integracion_plataformas.dto.UserDto;
import com.duoc.integracion_plataformas.enums.UserType;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user")
@Table(name = "users")
public class UserEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String username;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(name = "user_type")
  private UserType userType;

  @OneToMany(mappedBy = "user")
  private List<InvoiceEntity> invoices;

  public UserDto toUserDto() {
    return UserDto.builder()
        .id(this.id)
        .name(this.username)
        .lastName(this.lastName)
        .email(this.email)
        .password(this.password)
        .userType(this.userType)
        .build();
  }

}
