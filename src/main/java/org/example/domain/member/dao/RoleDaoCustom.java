package org.example.domain.member.dao;


import java.util.Optional;
import org.example.domain.member.domain.Role;
import org.example.domain.model.RoleEnum;

public interface RoleDaoCustom {
    Optional<Role> findByValue(RoleEnum value);
}
