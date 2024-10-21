package com.blu4ck.Palm_Coffee.zero.repository;

import com.blu4ck.Palm_Coffee.zero.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
