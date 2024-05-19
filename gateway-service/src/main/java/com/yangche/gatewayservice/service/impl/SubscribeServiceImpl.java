package com.yangche.gatewayservice.service.impl;

import static com.yangche.gatewayservice.constant.RoleType.*;

import com.yangche.gatewayservice.model.Role;
import com.yangche.gatewayservice.model.UserRole;
import com.yangche.gatewayservice.model.to.SubscribeTO;
import com.yangche.gatewayservice.repository.IRoleRepo;
import com.yangche.gatewayservice.repository.IUserRoleRepo;
import com.yangche.gatewayservice.service.SubscribeService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscribeServiceImpl implements SubscribeService {

    private final IRoleRepo roleRepo;

    private final IUserRoleRepo userRoleRepo;

    public SubscribeServiceImpl(IRoleRepo roleRepo, IUserRoleRepo userRoleRepo) {
        this.roleRepo = roleRepo;
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    @Transactional
    public String subscribe(SubscribeTO to) {
        var roleList = roleRepo.findByUserId(to.getUserId());
        if (checkIfSubscribe(roleList)) {
            return "Subscribed";
        }
        var roleId = roleRepo.findIdByRoleType(PAID.name());
        UserRole userRole = new UserRole();
        userRole.setUserId(to.getUserId());
        userRole.setRoleId(roleId);
        userRoleRepo.saveAndFlush(userRole);
        return "Subscribed successfully by userId: " + to.getUserId();
    }

    @Override
    @Transactional
    public String unsubscribe(SubscribeTO to) {
        var roleList = roleRepo.findByUserId(to.getUserId());
        var roleId = roleRepo.findIdByRoleType(PAID.name());
        if (checkIfSubscribe(roleList)) {
            userRoleRepo.deleteByRoleIdAndUserId(roleId, to.getUserId());
            return "Unsubscribed successfully by userId: " + to.getUserId();
        }
        return "you haven't subscribed";
    }

    private boolean checkIfSubscribe(List<Role> roleList) {
        return roleList.stream()
                .map(Role::getRoleType)
                .anyMatch(roleType -> roleType.equals(PAID.name()));
    }
}
