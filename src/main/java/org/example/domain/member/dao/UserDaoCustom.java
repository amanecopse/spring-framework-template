package org.example.domain.member.dao;


import java.util.Optional;
import org.example.domain.member.domain.User;

public interface UserDaoCustom {
    Optional<User> findByEmail(String email);
}
