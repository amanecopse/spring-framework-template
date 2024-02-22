package org.example.domain.member.dao;


import java.util.Optional;
import org.example.domain.member.domain.Role;
import org.example.domain.member.domain.User;
import org.example.domain.member.model.RoleEnum;

public interface RoleDaoCustom {
    Optional<Role> findByValue(RoleEnum value);
}
