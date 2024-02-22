package org.example.domain.member.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.domain.member.domain.QRole;
import org.example.domain.member.domain.QUser;
import org.example.domain.member.domain.Role;
import org.example.domain.member.domain.User;
import org.example.domain.member.model.RoleEnum;

@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDaoCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Role> findByValue(RoleEnum value) {
        QRole role = QRole.role;
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(role)
                        .where(role.value.eq(value))
                        .fetchOne()
        );
    }
}
