package org.example.domain.member.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.domain.member.domain.QUser;
import org.example.domain.member.domain.User;

@RequiredArgsConstructor
public class UserDaoImpl implements UserDaoCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<User> findByEmail(String email) {
        QUser user = QUser.user;
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(user)
                        .where(user.email.eq(email))
                        .fetchOne()
        );
    }
}
