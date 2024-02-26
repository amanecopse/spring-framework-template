package org.example.domain.member.dao;

import java.util.Optional;
import org.example.domain.member.domain.Role;
import org.example.domain.model.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface RoleDao extends JpaRepository<Role, Long>, RoleDaoCustom {
    @Override
    @NonNull
    Page<Role> findAll(@NonNull Pageable pageable);

    @Override
    @NonNull
    Optional<Role> findById(@NonNull Long id);

    @Override
    Optional<Role> findByValue(RoleEnum value);
}
