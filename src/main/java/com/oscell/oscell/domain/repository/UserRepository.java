package com.oscell.oscell.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscell.oscell.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);
    UserDetails findByUserName(String userName);
}
