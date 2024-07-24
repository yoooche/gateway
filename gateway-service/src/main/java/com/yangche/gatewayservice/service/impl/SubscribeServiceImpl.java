package com.yangche.gatewayservice.service.impl;

import static com.yangche.gatewayservice.constant.RoleType.*;

import com.yangche.gatewayservice.model.Role;
import com.yangche.gatewayservice.model.UserRole;
import com.yangche.gatewayservice.model.to.SubscribeTO;
import com.yangche.gatewayservice.repository.IRoleRepo;
import com.yangche.gatewayservice.repository.IUserRoleRepo;
import com.yangche.gatewayservice.service.SubscribeService;
import java.util.List;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PostAuthorize("hasRole('PAID')")
    public String subscribe(SubscribeTO to) {
        var roleList = roleRepo.findByUserId(to.getUserId());
        if (checkIfSubscribe(roleList)) {
            return "Subscribed";
        }
        var roleId = roleRepo.findIdByRoleType(PAID);
        UserRole userRole = new UserRole();
        userRole.setUserId(to.getUserId());
        userRole.setRoleId(roleId);
        userRoleRepo.saveAndFlush(userRole);
        refreshAuthentication(to);
        return "Subscribed successfully by userId: " + to.getUserId();
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('PAID')")
    public String unsubscribe(SubscribeTO to) {
        var roleId = roleRepo.findIdByRoleType(PAID);
        userRoleRepo.deleteByRoleIdAndUserId(roleId, to.getUserId());
        refreshAuthentication(to);
        return "unsubscribed successfully by userId: " + to.getUserId();
    }

    private boolean checkIfSubscribe(List<Role> roleList) {
        return roleList.stream()
                .map(Role::getRoleType)
                .anyMatch(roleType -> roleType.equals(PAID));
    }

    private void refreshAuthentication(SubscribeTO to) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var updatedAuthorities = roleRepo.findByUserId(to.getUserId()).stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType())).toList();
        var newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                authentication.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
