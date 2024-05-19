package com.yangche.gatewayservice.repository;

import com.yangche.gatewayservice.model.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepo extends JpaRepository<Role, Long> {

    @Query(value = "SELECT R.* FROM ROLE R "
            + "JOIN USER_ROLE U "
            + "ON R.ROLE_ID = U.ROLE_ID "
            + "WHERE U.USER_ID = :userId", nativeQuery = true)
    List<Role> findByUserId(Long userId);

    @Query("select r.roleId from Role r where r.roleType = ?1")
    Integer findIdByRoleType(String roleType);
}
