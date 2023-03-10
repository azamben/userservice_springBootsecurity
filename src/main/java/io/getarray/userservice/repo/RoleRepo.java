package io.getarray.userservice.repo;

import io.getarray.userservice.domain.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long > {
   Role findByName(String name);
}
