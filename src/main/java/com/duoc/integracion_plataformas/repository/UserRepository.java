package com.duoc.integracion_plataformas.repository;

import com.duoc.integracion_plataformas.entity.UserEntity;
import java.util.Optional;

import com.duoc.integracion_plataformas.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  @Query("select u from user u where u.userType = ?1 and u.username = ?2")
  Optional<UserEntity> testJPQL(UserType usertype, String name);

  @Query(
      "select COUNT(u) from user u where upper(u.username) = upper(?1) or upper(u.email) = upper(?2)")
  Long checkRepeated(String username, String email);

  Optional<UserEntity> findByEmail(String email);

  Optional<UserEntity> findByPasswordAndEmail(String password, String email);

  Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
