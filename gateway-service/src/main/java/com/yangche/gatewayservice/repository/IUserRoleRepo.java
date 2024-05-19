package com.yangche.gatewayservice.repository;

import com.yangche.gatewayservice.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleRepo extends JpaRepository<UserRole, Long> {
}
