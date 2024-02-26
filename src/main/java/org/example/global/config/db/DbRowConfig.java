package org.example.global.config.db;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.example.domain.member.dao.RoleDao;
import org.example.domain.member.domain.Role;
import org.example.domain.model.RoleEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
@RequiredArgsConstructor
public class DbRowConfig implements InitializingBean {
    private final RoleDao roleDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        insertRoles();
    }

    private void insertRoles() {
        Arrays.stream(RoleEnum.values()).forEach(this::insertRole);
    }

    private void insertRole(RoleEnum value) {
        if (roleDao.findByValue(value).isEmpty()) {
            trySave(roleDao, Role.builder()
                    .value(value)
                    .build());
        }
    }

    private <T> void trySave(JpaRepository<T, Long> dao, T entity) {
        try {
            dao.save(entity);
        } catch (Exception e) {
            return;
        }
    }
}
