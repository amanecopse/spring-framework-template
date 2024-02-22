package org.example.domain.member.application;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.domain.member.dao.UserDao;
import org.example.domain.member.domain.User;
import org.example.domain.member.dto.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByEmail(username);
        if (user.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return userMapper.toPrinciple(user.get());
    }
}
