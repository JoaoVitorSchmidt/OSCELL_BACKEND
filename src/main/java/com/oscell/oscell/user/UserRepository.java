package com.oscell.oscell.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscell.oscell.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);
    User findByUserName(String userName);    
}
