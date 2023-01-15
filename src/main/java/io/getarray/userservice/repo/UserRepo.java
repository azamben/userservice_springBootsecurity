package io.getarray.userservice.repo;

import io.getarray.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User , Long > {
    User findByUsername(String username);
}
