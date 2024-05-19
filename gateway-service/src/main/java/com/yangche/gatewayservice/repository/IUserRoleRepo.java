package com.yangche.gatewayservice.repository;

import com.yangche.gatewayservice.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleRepo extends JpaRepository<UserRole, Long> {

    @Modifying
    @Query("delete from UserRole u where u.roleId = ?1 and u.userId = ?2")
    void deleteByRoleIdAndUserId(Long roleId, Long userId);
}
