package com.yangche.gatewayservice.repository;

import com.yangche.gatewayservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u where u.userId = ?1")
    User findByUserId(Long userId);
}
