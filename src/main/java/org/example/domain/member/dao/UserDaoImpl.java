package org.example.domain.member.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.domain.board.dao.SampleDaoCustom;
import org.example.domain.board.domain.QSample;
import org.example.domain.board.domain.Sample;
import org.example.domain.board.dto.SampleSearchRequest;
import org.example.domain.member.domain.QUser;
import org.example.domain.member.domain.User;
import org.example.global.common.request.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
